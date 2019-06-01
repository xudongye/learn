package me.own.learn.pubaccount.service.model;

/**
 * Created by CN-LEBOOK-YANGLI on 2017/3/16.
 *
 * Move code for wechat pub account temp auth token
 *
 * This kind of token would not be persisted, only used for menu click authorization
 */
public class PubAccountAuthToken {

    private String accessToken;

    private int expiresIn;

    private String openid;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
