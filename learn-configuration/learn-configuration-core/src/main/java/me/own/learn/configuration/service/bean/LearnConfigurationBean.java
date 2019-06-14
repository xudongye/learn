package me.own.learn.configuration.service.bean;

import me.own.learn.configuration.template.LearnConfigurationTemplate;
import me.own.learn.configuration.template.LearnFileConfigurationTemplate;
import me.own.learn.configuration.template.LearnTmcConfigurationTemplate;

/**
 * @author yexudong
 * @date 2019/4/17 16:14
 */
public class LearnConfigurationBean implements LearnConfigurationTemplate {

    private String domain;

    private LearnTmcConfigurationBean tmc;

    private LearnFileConfigurationBean file;

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setTmc(LearnTmcConfigurationBean tmc) {
        this.tmc = tmc;
    }

    public void setFile(LearnFileConfigurationBean file) {
        this.file = file;
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public LearnTmcConfigurationBean getTmc() {
        return tmc;
    }

    @Override
    public LearnFileConfigurationBean getFile() {
        return file;
    }
}
