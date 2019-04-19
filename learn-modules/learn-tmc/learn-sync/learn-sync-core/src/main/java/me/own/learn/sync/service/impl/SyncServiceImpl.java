package me.own.learn.sync.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.own.learn.commons.base.utils.http.HttpUtils;
import me.own.learn.commons.base.utils.mapper.Mapper;
import me.own.learn.configuration.delegate.LearnConfigurationServiceDelegate;
import me.own.learn.sync.bo.CountryBo;
import me.own.learn.sync.bo.RequestBo;
import me.own.learn.sync.bo.ResponseBo;
import me.own.learn.sync.constant.SyncConstant;
import me.own.learn.sync.db.CountryRepository;
import me.own.learn.sync.exception.TitanServerCallOnFailedException;
import me.own.learn.sync.po.Country;
import me.own.learn.sync.service.SearchService;
import me.own.learn.sync.service.SignatureService;
import me.own.learn.sync.service.SyncService;
import me.own.learn.sync.vo.CountryVo;
import me.own.learn.sync.vo.SignatureVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

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
    public List<CountryVo> syncCountries() {
        Object result = responseBody(SyncConstant.Signature.queryCountryList.getName());
        LinkedHashMap<String, Object> countryMap = (LinkedHashMap<String, Object>) result;
        List<CountryBo> countryBos = mapper.convertValue(countryMap.get("countries"),
                new TypeReference<List<CountryBo>>() {
                });
        for (CountryBo countryBo : countryBos) {
            searchService.save(countryBo);
        }
        return Mapper.Default().mapArray(countryBos, CountryVo.class);
    }

    @Override
    public void syncCities() {

    }

    @Override
    public void syncHotels() {

    }


    private Object responseBody(String requestType) {

        String partnerCode = delegate.getConfiguration().getTmc().getPartnerCode();
        String version = delegate.getConfiguration().getTmc().getVersion();
        SignatureVo signature = signatureService.getByRequestType(requestType);

        RequestBo params = new RequestBo();

        params.setPartnerCode(partnerCode);
        params.setVersion(version);
        params.setRequestType(requestType);
        params.setSignature(signature.getSignature());
        params.setTimestamp(signature.getTimestamp());

        String titanUrl = BASE_URL.replace("{{requestType}}", requestType);
        String result = HttpUtils.postJsonStr(params.toString(), titanUrl);
        if (result != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                ResponseBo responseBody = mapper.readValue(result, ResponseBo.class);
                if (responseBody.getRetrunCode().equals("000")) {
                    return responseBody.getBussinessResponse();
                }
                LOGGER.debug("titan server call on error{}", result);
            } catch (IOException e) {
                LOGGER.error("parse json result error", e);
            }
        }
        throw new TitanServerCallOnFailedException();
    }


}
