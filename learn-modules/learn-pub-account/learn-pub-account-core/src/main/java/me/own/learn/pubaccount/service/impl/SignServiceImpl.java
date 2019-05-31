package me.own.learn.pubaccount.service.impl;

import me.lebooks.commons.wechat.pubaccount.sign.JsApiSign;
import me.lebooks.lishu.configuration.service.LishuConfigurationService;
import me.lebooks.lishu.pubaccount.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SignServiceImpl implements SignService {
    @Autowired
    private LishuConfigurationService lishuConfigurationService;

    @Override
    public Map<String, Object> signUrl(String appId, String url) {
        List<String> trustedDomains = lishuConfigurationService.getConfiguration().getPay().getTrustedDomains();
        for (String trustedDomain : trustedDomains) {
            if (url.startsWith(trustedDomain)) {
                return JsApiSign.signUrl(appId, url);
            }
        }
        throw new IllegalArgumentException("sign url must start with " + trustedDomains);
    }
}
