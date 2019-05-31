package me.own.learn.sync.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.own.commons.base.utils.http.HttpUtils;
import me.own.learn.sync.bo.SignatureResultBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:simonye
 * @date 22:00 2019/4/16
 **/
public class SignatureUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignatureUtils.class);

    private static String partnerCode;

    private static String securityKey;

    private static final String RequestUrl = "http://www.fangcang.com/tmc-hub/help/createSignature";

    static {
        try {
            InitialContext context = new InitialContext();
            partnerCode = (String) context.lookup("java:comp/env/tmc/partnerCode");
            securityKey = (String) context.lookup("java:comp/env/tmc/securityKey");
        } catch (NamingException e) {
            LOGGER.error("FATAL:titan account initialize error.", e);
        }
    }

    public static SignatureResultBo requestSignature(String requestType) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> form = new HashMap<>();
        form.put("partnerCode", partnerCode);
        form.put("securityKey", securityKey);
        form.put("requestType", requestType);
        try {
            String result = HttpUtils.postJsonStr(mapper.writeValueAsString(form), RequestUrl);
            LOGGER.info("request signature result {}", result);
            return mapper.readValue(result, SignatureResultBo.class);
        } catch (IOException e) {
            LOGGER.error("parse json error:", e);
        }
        return null;
    }


}
