package me.own.learn.configuration.service.bean;


import me.own.learn.configuration.template.LearnPayConfigurationTemplate;

public class LearnPayConfigurationBean implements LearnPayConfigurationTemplate {

    private String alipayCallback;

    private String wxpayCallback;

    @Override
    public String getAlipayCallback() {
        return alipayCallback;
    }

    public void setAlipayCallback(String alipayCallback) {
        this.alipayCallback = alipayCallback;
    }

    @Override
    public String getWxpayCallback() {
        return wxpayCallback;
    }

    public void setWxpayCallback(String wxpayCallback) {
        this.wxpayCallback = wxpayCallback;
    }
}
