package me.own.learn.pubaccount.service.impl;

import me.own.commons.wechat.pubaccount.sign.JsApiSign;
import me.own.learn.pubaccount.service.SignService;
import me.own.learn.pubconfiguration.service.PubConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SignServiceImpl implements SignService {

    @Autowired
    private PubConfigurationService pubConfigurationService;

    @Override
    public Map<String, Object> signUrl(String appId, String url) {
        List<String> trustedDomains = pubConfigurationService.domains(appId);
        for (String trustedDomain : trustedDomains) {
            if (url.startsWith(trustedDomain)) {
                return JsApiSign.signUrl(appId, url);
            }
        }
        throw new IllegalArgumentException("sign url must start with " + trustedDomains);
    }
}
