package me.own.learn.sync.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import me.own.learn.commons.base.utils.mapper.Mapper;
import me.own.learn.sync.bo.*;
import me.own.learn.sync.bo.responseBo.*;
import me.own.learn.sync.constant.SyncConstant;
import me.own.learn.sync.exception.ResultSizeEmptyException;
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

import java.util.*;

import static me.own.learn.sync.utils.ResponseUtils.businessResponse;
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


    @Override
    public List<CountryVo> matchCountries(String[] countryNames) {
        String countryMap = businessResponse(new HashMap<>(), signatureService.getByRequestType(SyncConstant.Signature.queryCountryList.getName()));
        ResponseBaseBo<ResponseCountryBo> response = readValue(countryMap, new TypeReference<ResponseBaseBo<ResponseCountryBo>>() {
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
        String provinceMap = businessResponse(businessRequest, signatureService.getByRequestType(SyncConstant.Signature.queryCityList.getName()));
        ResponseBaseBo<ResponseCityBo> response = readValue(provinceMap, new TypeReference<ResponseBaseBo<ResponseCityBo>>() {
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
        String dtMap = businessResponse(businessRequest, signatureService.getByRequestType(SyncConstant.Signature.queryDistrictList.getName()));
        ResponseBaseBo<ResponseDistrictBo> response = readValue(dtMap, new TypeReference<ResponseBaseBo<ResponseDistrictBo>>() {
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
        String bnMap = businessResponse(businessRequest, signatureService.getByRequestType(SyncConstant.Signature.queryBusinessList.getName()));
        ResponseBaseBo<ResponseBusinessBo> response = readValue(bnMap, new TypeReference<ResponseBaseBo<ResponseBusinessBo>>() {
        });
        List<BusinessKeyBo> businessKeyBos = response.getBussinessResponse().getBusinesses();
        for (BusinessKeyBo businessKeyBo : businessKeyBos) {
            searchService.insertBusinessKey(businessKeyBo);
        }
        return Mapper.Default().mapArray(businessKeyBos, BusinessKeyVo.class);
    }

    @Override
    public void syncHotels(String cityCode, String hotelName) {
        
    }

}
