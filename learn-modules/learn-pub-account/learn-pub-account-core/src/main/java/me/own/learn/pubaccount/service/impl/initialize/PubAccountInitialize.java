package me.own.learn.pubaccount.service.impl.initialize;

import me.own.commons.wechat.pubaccount.common.Configure;
import me.own.commons.wechat.pubaccount.common.ConfigureManager;
import me.own.learn.pubconfiguration.service.PubConfigurationService;
import me.own.learn.pubconfiguration.vo.PubConfigurationVo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author yexudong
 * @date 2019/5/31 14:53
 */
@Component
public class PubAccountInitialize {

    private static final Logger LOGGER = LoggerFactory.getLogger(PubAccountInitialize.class);

    @Autowired
    private PubConfigurationService pubConfigurationService;

    @PostConstruct
    public void pubAccountConfigurationInitializer() {
        List<PubConfigurationVo> pubConfigurationVos = pubConfigurationService.getActivePubAccountConfiguration();
        if (CollectionUtils.isEmpty(pubConfigurationVos)) {
            LOGGER.info("had no pub account configuration!");
            return;
        }
        for (PubConfigurationVo pubConfigurationVo : pubConfigurationVos) {
            Configure configure = new Configure();
            configure.setAppID(pubConfigurationVo.getPubAccountAppId());
            configure.setAppSecret(pubConfigurationVo.getPubAccountAppSecret());
            configure.setToken(pubConfigurationVo.getPubAccountToken());
            configure.setKey(pubConfigurationVo.getWxpayKey());
            configure.setMchID(pubConfigurationVo.getWxpayMerchantId());
            ConfigureManager.addConfigure(configure.getAppID(), configure);
            LOGGER.info("wechat pub account initialized success, appId:{}， appSecret:{},token :{}", configure.getAppID(), configure.getAppSecret(), configure.getToken());

        }


    }
}
