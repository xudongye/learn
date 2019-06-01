package me.own.learn.configuration.service.bean;

import me.own.learn.configuration.template.LearnConfigurationTemplate;
import me.own.learn.configuration.template.LearnTmcConfigurationTemplate;

/**
 * @author yexudong
 * @date 2019/4/17 16:14
 */
public class LearnConfigurationBean implements LearnConfigurationTemplate {

    private String domain;

    private LearnTmcConfigurationBean tmc;

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setTmc(LearnTmcConfigurationBean tmc) {
        this.tmc = tmc;
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public LearnTmcConfigurationBean getTmc() {
        return tmc;
    }
}
