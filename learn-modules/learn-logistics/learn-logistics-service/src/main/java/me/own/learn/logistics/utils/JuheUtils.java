package me.own.learn.logistics.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.own.commons.base.utils.http.HttpUtils;
import me.own.learn.logistics.bo.CompanyBo;
import me.own.learn.logistics.bo.LogisticsBo;
import me.own.learn.logistics.bo.LogisticsRequestBo;
import me.own.learn.logistics.bo.LogisticsResponseBo;
import me.own.learn.logistics.exception.JuheCallOnFailureException;
import me.own.learn.logistics.exception.JuheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yexudong
 * @date 2019/9/4 13:38
 */
public class JuheUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JuheUtils.class);

    private static final String JUHE_KEY = "457ae20241a4c6ebe126e9e444c2773d";

    private static final String JUHE_URL = "http://v.juhe.cn/exp";

    private static ObjectMapper mapper = new ObjectMapper();

    //快递公司编号对照表
    public static LogisticsResponseBo onCompanySupport() {
        Map<String, String> params = new HashMap<>();
        params.put("key", JUHE_KEY);
        String result = HttpUtils.post(params, JUHE_URL + "/com");
        LogisticsResponseBo responseBo;
        try {
            LOGGER.info("logistics company {}", result);
            responseBo = mapper.readValue(result,
                    new TypeReference<LogisticsResponseBo<List<CompanyBo>>>() {
                    });
        } catch (IOException e) {
            throw new JuheCallOnFailureException();
        }
        if (responseBo.getError_code() != 0) {
            throw new JuheException(responseBo.getReason());
        }
        return responseBo;
    }

    //物流状态查询
    public static LogisticsResponseBo onLogistics(String com, String no, String senderPhone, String receiverPhone) {
        Map<String, String> params = new HashMap<>();
        params.put("com", com);
        params.put("no", no);
        params.put("senderPhone", senderPhone);
        params.put("receiverPhone", receiverPhone);
        params.put("dtype", "json");
        params.put("key", JUHE_KEY);
        String result = HttpUtils.post(params, JUHE_URL + "/index");
        LogisticsResponseBo responseBo;
        try {
            LOGGER.info("logistics status {}", result);
            responseBo = mapper.readValue(result, new TypeReference<LogisticsResponseBo<LogisticsBo>>() {
            });
        } catch (IOException e) {
            throw new JuheCallOnFailureException();
        }
        if (responseBo.getError_code() != 0) {
            throw new JuheException(responseBo.getReason());
        }
        return responseBo;
    }

    public static void main(String[] args) {
        LogisticsResponseBo responseBo = onCompanySupport();
        System.out.println(responseBo.toString());
        LogisticsResponseBo logisticsResponseBo = onLogistics("zto", "73118931594837", "9836", "");
        System.out.println(logisticsResponseBo.toString());

    }

}
