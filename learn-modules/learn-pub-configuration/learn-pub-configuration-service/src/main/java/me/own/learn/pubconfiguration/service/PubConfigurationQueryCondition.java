package me.own.learn.pubconfiguration.service;

/**
 * @author yexudong
 * @date 2019/8/10 20:29
 */
public class PubConfigurationQueryCondition {
    private String name;
    private String pubAccountAppId;
    private String wxpayKey;
    private String wxpayMerchantId;
    private String domain;
    private Boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPubAccountAppId() {
        return pubAccountAppId;
    }

    public void setPubAccountAppId(String pubAccountAppId) {
        this.pubAccountAppId = pubAccountAppId;
    }

    public String getWxpayKey() {
        return wxpayKey;
    }

    public void setWxpayKey(String wxpayKey) {
        this.wxpayKey = wxpayKey;
    }

    public String getWxpayMerchantId() {
        return wxpayMerchantId;
    }

    public void setWxpayMerchantId(String wxpayMerchantId) {
        this.wxpayMerchantId = wxpayMerchantId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
