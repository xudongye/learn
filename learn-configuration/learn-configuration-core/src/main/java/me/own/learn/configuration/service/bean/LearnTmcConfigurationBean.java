package me.own.learn.configuration.service.bean;

import me.own.learn.configuration.template.LearnTmcConfigurationTemplate;

/**
 * @author yexudong
 * @date 2019/4/17 16:15
 */
public class LearnTmcConfigurationBean implements LearnTmcConfigurationTemplate {

    private String partnerCode;
    private String version;

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String getPartnerCode() {
        return partnerCode;
    }

    @Override
    public String getVersion() {
        return version;
    }
}
