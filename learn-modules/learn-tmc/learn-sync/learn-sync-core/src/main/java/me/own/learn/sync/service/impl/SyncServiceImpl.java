package me.own.learn.sync.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.own.learn.commons.base.utils.http.HttpUtils;
import me.own.learn.commons.base.utils.mapper.Mapper;
import me.own.learn.configuration.delegate.LearnConfigurationServiceDelegate;
import me.own.learn.sync.bo.*;
import me.own.learn.sync.bo.responseBo.ResponseBaseBo;
import me.own.learn.sync.bo.responseBo.ResponseCityBo;
import me.own.learn.sync.bo.responseBo.ResponseCountryBo;
import me.own.learn.sync.constant.SyncConstant;
import me.own.learn.sync.db.CountryRepository;
import me.own.learn.sync.exception.CountryNotFoundException;
import me.own.learn.sync.exception.TitanServerCallOnFailedException;
import me.own.learn.sync.po.Country;
import me.own.learn.sync.service.SearchService;
import me.own.learn.sync.service.SignatureService;
import me.own.learn.sync.service.SyncService;
import me.own.learn.sync.vo.CityVo;
import me.own.learn.sync.vo.CountryVo;
import me.own.learn.sync.vo.SignatureVo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @author:simonye
 * @date 21:37 2019/4/16
 **/
@Service
public class SyncServiceImpl implements SyncService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SyncServiceImpl.class);

    private LearnConfigurationServiceDelegate delegate = LearnConfigurationServiceDelegate.getInstance();

    private static final String BASE_URL = "http://www.fangcang.com/tmc-hub/{{requestType}}";

    @Autowired
    private SignatureService signatureService;

    @Autowired
    private SearchService searchService;

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<CountryVo> syncCountries(String[] countryNames) {
        String countryMap = businessResponse(SyncConstant.Signature.queryCountryList.getName(), new HashMap<>());
        ResponseBaseBo<ResponseCountryBo> response = new ResponseBaseBo<ResponseCountryBo>();
        try {
            response = mapper.readValue(countryMap, ResponseBaseBo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!response.getRetrunCode().equals("000")) {
            throw new TitanServerCallOnFailedException();
        }
        List<CountryBo> countryBos = response.getBussinessResponse().getCountries();

        if (countryNames.length != 0) {
            countryBos = matchCounties(countryBos, countryNames);
        }
        for (CountryBo countryBo : countryBos) {
            searchService.save(countryBo);
        }
        return Mapper.Default().mapArray(countryBos, CountryVo.class);
    }

    private List<CountryBo> matchCounties(List<CountryBo> countryBos, String[] countryNames) {
        List<CountryBo> countryBoList = new ArrayList<>();
        for (int i = 0; i < countryBos.size(); i++) {
            for (int j = 0; j < countryNames.length; j++) {
                if (countryBos.get(i).getCountryName().equals(countryNames[j])) {
                    countryBoList.add(countryBos.get(i));
                }
            }
        }
        return countryBoList;
    }

    @Override
    public CountryVo syncCities(String countryCode, String[] cityNames) {

        CountryBo countryBo = null;
        try {
            CountryVo countryVo = searchService.findByCountryCode(countryCode);
            countryBo = Mapper.Default().map(countryVo, CountryBo.class);
        } catch (CountryNotFoundException nfe) {
            countryBo = new CountryBo();
            LOGGER.debug("country {} not exit.", countryBo.getCountryCode());
        }
        Map<String, Object> businessRequest = new HashMap<>();
        businessRequest.put("countryCode", countryCode);
        String provinceMap = businessResponse(SyncConstant.Signature.queryCityList.getName(), businessRequest);
        ResponseBaseBo<ResponseCityBo> response = mapper.convertValue(provinceMap, new TypeReference<ResponseBaseBo<ResponseCityBo>>() {
        });
        if (!response.getRetrunCode().equals("000")) {
            throw new TitanServerCallOnFailedException();
        }
        List<ProvinceBo> provinceBos = response.getBussinessResponse().getProvinces();
        if (cityNames.length != 0) {
            provinceBos = matchProvince(provinceBos, cityNames);
        }
        countryBo.setProvinces(provinceBos);
        CountryVo countryVo = searchService.refresh(countryBo);
        LOGGER.info("refresh country {} province {} and city {}", countryVo.getCountryName(),
                countryVo.getProvinces().get(0).getProvinceName(),
                countryVo.getProvinces().get(0).getCitys().get(0).getCityName());
        return countryVo;
    }

    private List<ProvinceBo> matchProvince(List<ProvinceBo> provinceBos, String[] cityNames) {
        List<ProvinceBo> provinceBoList = new ArrayList<>();
        for (String cityName : cityNames) {
            for (ProvinceBo provinceBo : provinceBos) {
                List<CityBo> cityBoList = new ArrayList<>();
                for (CityBo cityBo : provinceBo.getCitys()) {
                    if (cityBo.getCityName().equals(cityName)) {
                        cityBoList.add(cityBo);
                    }
                }
                if (CollectionUtils.isNotEmpty(cityBoList)) {
                    ProvinceBo provinceBo1 = new ProvinceBo();
                    provinceBo1.setProvinceName(provinceBo.getProvinceName());
                    provinceBo1.setProvinceCode(provinceBo.getProvinceCode());
                    provinceBo1.setCitys(cityBoList);
                    provinceBoList.add(provinceBo1);
                }
            }
        }
        return provinceBoList;
    }

    @Override
    public void syncHotels() {

    }


    private String businessResponse(String requestType, Map<String, Object> businessRequest) {
        String partnerCode = delegate.getConfiguration().getTmc().getPartnerCode();
        String version = delegate.getConfiguration().getTmc().getVersion();
        SignatureVo signature = signatureService.getByRequestType(requestType);

        Map<String, Object> header = new HashMap<>();
        header.put("partnerCode", partnerCode);
        header.put("version", version);
        header.put("requestType", requestType);
        header.put("signature", signature.getSignature());
        header.put("timestamp", signature.getTimestamp());

        Map<String, Object> params = new HashMap<>();
        params.put("businessRequest", businessRequest);
        params.put("header", header);

        return getResponseBody(requestType, params);
    }

    private String getResponseBody(String requestType, Map<String, Object> params) {
        String titanUrl = BASE_URL.replace("{{requestType}}", requestType);
        try {
            String result = HttpUtils.postJsonStr(mapper.writeValueAsString(params), titanUrl);
            LOGGER.error("titan server error response {}", result);
            return result;
        } catch (IOException e) {
            LOGGER.error("parse json result error", e);
        }
        return null;
    }

}
