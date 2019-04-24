package me.own.learn.sync.utils;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import me.own.learn.commons.base.utils.http.HttpUtils;
import me.own.learn.configuration.delegate.LearnConfigurationServiceDelegate;
import me.own.learn.sync.bo.responseBo.ResponseBaseBo;
import me.own.learn.sync.exception.ParameterParseException;
import me.own.learn.sync.exception.ResponseFailedException;
import me.own.learn.sync.exception.TitanServerCallOnFailedException;
import me.own.learn.sync.vo.SignatureVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yexudong
 * @date 2019/4/22 12:25
 */
public class ResponseUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseUtils.class);

    private static LearnConfigurationServiceDelegate delegate = LearnConfigurationServiceDelegate.getInstance();

    private static final String BASE_URL = "http://www.fangcang.com/tmc-hub/{{requestType}}";

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        //对于为null的字段不进行序列化
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        //对于未知属性不进行反序列化
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        //无论对象中的值只有不为null的才进行序列化
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static <E> ResponseBaseBo<E> readValue(Map<String, Object> businessRequest, SignatureVo signature, TypeReference typeReference) {
        ResponseBaseBo<E> response = null;
        try {
            response = mapper.readValue(businessResponse(businessRequest, signature), typeReference);
        } catch (IOException e) {
            LOGGER.error("json parse error {}", e);
        }
        if (response == null) {
            throw new TitanServerCallOnFailedException();
        }
        if (!response.getRetrunCode().equals("000")) {
            throw new ResponseFailedException(response.getRetrunMsg());
        }
        return response;
    }


    private static String businessResponse(Map<String, Object> businessRequest, SignatureVo signature) {
        String partnerCode = delegate.getConfiguration().getTmc().getPartnerCode();
        String version = delegate.getConfiguration().getTmc().getVersion();
        Map<String, Object> header = new HashMap<>();
        header.put("partnerCode", partnerCode);
        header.put("version", version);
        header.put("requestType", signature.getRequestType());
        header.put("signature", signature.getSignature());
        header.put("timestamp", signature.getTimestamp());

        Map<String, Object> params = new HashMap<>();
        params.put("businessRequest", businessRequest);
        params.put("header", header);

        return getResponseBody(signature.getRequestType(), params);
    }

    private static String getResponseBody(String requestType, Map<String, Object> params) {
        String titanUrl = BASE_URL.replace("{{requestType}}", requestType);
        try {
            String result = HttpUtils.postJsonStr(mapper.writeValueAsString(params), titanUrl);
            LOGGER.info("print titan server response {}", result);
            return result;
        } catch (IOException e) {
            LOGGER.error("parse json result error", e);
            throw new ParameterParseException();
        }
    }

}
