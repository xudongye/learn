package me.own.learn.pubconfiguration.dto;

/**
 * @author yexudong
 * @date 2019/5/31 10:54
 */
public class PubConfigurationDto {
    private Long id;
    private String name;
    private String logo;
    private String pubAccountAppId;
    private String pubAccountAppSecret;
    private String pubAccountToken;

    private String wxpayKey;
    private String wxpayMerchantId;

    private String domain;
    private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPubAccountAppId() {
        return pubAccountAppId;
    }

    public void setPubAccountAppId(String pubAccountAppId) {
        this.pubAccountAppId = pubAccountAppId;
    }

    public String getPubAccountAppSecret() {
        return pubAccountAppSecret;
    }

    public void setPubAccountAppSecret(String pubAccountAppSecret) {
        this.pubAccountAppSecret = pubAccountAppSecret;
    }

    public String getPubAccountToken() {
        return pubAccountToken;
    }

    public void setPubAccountToken(String pubAccountToken) {
        this.pubAccountToken = pubAccountToken;
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
