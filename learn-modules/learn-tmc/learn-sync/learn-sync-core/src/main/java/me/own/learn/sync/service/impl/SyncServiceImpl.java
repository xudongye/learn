package me.own.learn.sync.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import me.own.learn.commons.base.utils.mapper.Mapper;
import me.own.learn.sync.bo.*;
import me.own.learn.sync.bo.responseBo.*;
import me.own.learn.sync.constant.SyncConstant;
import me.own.learn.sync.dao.HotelBaseInfoDao;
import me.own.learn.sync.dao.HotelIdDao;
import me.own.learn.sync.exception.ResultSizeEmptyException;
import me.own.learn.sync.po.HotelBaseInfo;
import me.own.learn.sync.service.SearchService;
import me.own.learn.sync.service.SignatureService;
import me.own.learn.sync.service.SyncService;
import me.own.learn.sync.vo.BusinessKeyVo;
import me.own.learn.sync.vo.CountryVo;
import me.own.learn.sync.vo.DistrictKeyVo;
import me.own.learn.sync.vo.ProvinceVo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.BatchUpdateException;
import java.util.*;

import static me.own.learn.sync.utils.ResponseUtils.readValue;

/**
 * @author:simonye
 * @date 21:37 2019/4/16
 **/
@Service
public class SyncServiceImpl implements SyncService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SyncServiceImpl.class);

    @Autowired
    private SignatureService signatureService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private HotelIdDao hotelIdDao;

    @Autowired
    private HotelBaseInfoDao hotelBaseInfoDao;


    @Override
    public List<CountryVo> matchCountries(String[] countryNames) {
        ResponseBaseBo<ResponseCountryBo> response = readValue(new HashMap<>(),
                signatureService.getByRequestType(SyncConstant.Signature.queryCountryList.getName()),
                new TypeReference<ResponseBaseBo<ResponseCountryBo>>() {
                });
        List<CountryBo> countryBos = response.getBussinessResponse().getCountries();
        List<CountryBo> resultCns = new ArrayList<>();
        if (countryNames.length != 0) {
            for (String countryName : countryNames) {
                for (CountryBo countryBo : countryBos) {
                    if (countryBo.getCountryName().contains(countryName)) {
                        resultCns.add(countryBo);
                    }
                }
            }
        } else {
            resultCns = countryBos;
        }
        return Mapper.Default().mapArray(resultCns, CountryVo.class);
    }

    @Override
    public List<ProvinceVo> syncCities(String countryCode, String countryName) {

        Map<String, Object> businessRequest = new HashMap<>();
        businessRequest.put("countryCode", countryCode);
        ResponseBaseBo<ResponseCityBo> response = readValue(businessRequest,
                signatureService.getByRequestType(SyncConstant.Signature.queryCityList.getName()),
                new TypeReference<ResponseBaseBo<ResponseCityBo>>() {
                });
        List<ProvinceBo> provinceBos = response.getBussinessResponse().getProvinces();
        if (CollectionUtils.isEmpty(provinceBos)) {
            throw new ResultSizeEmptyException();
        }
        for (ProvinceBo provinceBo : provinceBos) {
            for (CityBo cityBo : provinceBo.getCitys()) {
                CityKeyBo cityKeyBo = new CityKeyBo();
                cityKeyBo.setCountryName(countryName);
                cityKeyBo.setProvinceName(provinceBo.getProvinceName());
                cityKeyBo.setCityName(cityBo.getCityName());
                cityKeyBo.setCityCode(cityBo.getCityCode());
                searchService.insertCityKey(cityKeyBo);
            }
        }
        return Mapper.Default().mapArray(provinceBos, ProvinceVo.class);
    }

    @Override
    public List<DistrictKeyVo> syncDistricts(String cityCode) {
        Map<String, Object> businessRequest = new HashMap<>();
        businessRequest.put("cityCode", cityCode);
        ResponseBaseBo<ResponseDistrictBo> response = readValue(businessRequest,
                signatureService.getByRequestType(SyncConstant.Signature.queryDistrictList.getName()),
                new TypeReference<ResponseBaseBo<ResponseDistrictBo>>() {
                });
        List<DistrictKeyBo> districtKeyBos = response.getBussinessResponse().getDistricts();
        for (DistrictKeyBo districtKeyBo : districtKeyBos) {
            searchService.insertDistrictKey(districtKeyBo);
        }
        return Mapper.Default().mapArray(districtKeyBos, DistrictKeyVo.class);
    }

    @Override
    public List<BusinessKeyVo> syncBusiness(String cityCode) {

        Map<String, Object> businessRequest = new HashMap<>();
        businessRequest.put("cityCode", cityCode);
        ResponseBaseBo<ResponseBusinessBo> response = readValue(businessRequest,
                signatureService.getByRequestType(SyncConstant.Signature.queryBusinessList.getName()),
                new TypeReference<ResponseBaseBo<ResponseBusinessBo>>() {
                });
        List<BusinessKeyBo> businessKeyBos = response.getBussinessResponse().getBusinesses();
        for (BusinessKeyBo businessKeyBo : businessKeyBos) {
            searchService.insertBusinessKey(businessKeyBo);
        }
        return Mapper.Default().mapArray(businessKeyBos, BusinessKeyVo.class);
    }

    @Override
    @Transactional
    public void syncHotels(String cityCode) {
        int pageNo = 1;
        ResponseBaseBo<ResponseHotelIdBo> response = null;
        do {
            Map<String, Object> businessRequest = new HashMap<>();
            businessRequest.put("cityCode", cityCode);
            businessRequest.put("pageNo", pageNo);
            response = readValue(businessRequest,
                    signatureService.getByRequestType(SyncConstant.Signature.queryHotelIdList.getName()),
                    new TypeReference<ResponseBaseBo<ResponseHotelIdBo>>() {
                    });
            pageNo++;
            hotelIdDao.batchAddHotelIds(response.getBussinessResponse().getHotelIds(), cityCode);

        } while (pageNo <= response.getBussinessResponse().getTotalPage());
        LOGGER.info("sync total page hotelIds {} success", response.getBussinessResponse().getTotalPage());
    }

    @Override
    @Transactional
    public void syncHotelInfos(String cityCode) {
        List<Long> hotelIds = hotelIdDao.getIdsByCityCode(cityCode);
        List<List<Long>> tenIds = Lists.partition(hotelIds, 10);
        for (List<Long> tenId : tenIds) {
            ResponseBaseBo<ResponseHotelInfoBo> response = queryHotelInfo(tenId);
            List<HotelInfoBo> hotelInfos = response.getBussinessResponse().getHotelInfos();
            if (CollectionUtils.isNotEmpty(hotelInfos)) {
                hotelBaseInfoDao.batchAddHotelInfo(Mapper.Default().mapArray(hotelInfos, HotelBaseInfo.class));
            } else {
                LOGGER.error("hotelInfo is null by ids {}", tenId);
                hotelIdDao.updateNeedSync(tenId);
            }
        }
    }

    @Override
    @Transactional
    public void completeHotelInfoByHotelId(Long hotelId) {
        List<Long> ids = new ArrayList<>();
        ids.add(hotelId);
        ResponseBaseBo<ResponseHotelInfoBo> response = queryHotelInfo(ids);

    }

    private ResponseBaseBo<ResponseHotelInfoBo> queryHotelInfo(List<Long> tenId) {
        Map<String, Object> businessRequest = new HashMap<>();
        businessRequest.put("hotelIds", tenId);
        ResponseBaseBo<ResponseHotelInfoBo> response = readValue(businessRequest,
                signatureService.getByRequestType(SyncConstant.Signature.queryHotelInfo.getName()),
                new TypeReference<ResponseBaseBo<ResponseHotelInfoBo>>() {
                });
        return response;
    }

}
