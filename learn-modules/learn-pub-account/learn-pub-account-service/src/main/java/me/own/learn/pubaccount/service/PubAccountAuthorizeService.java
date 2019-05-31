package me.own.learn.pubaccount.service;

import me.own.learn.pubaccount.service.model.PubAccountAuthToken;

/**
 * Created by CN-LEBOOK-YANGLI on 2017/3/16.
 */
public interface PubAccountAuthorizeService {

    /***
     * Get access_token by temp auth code
     * @param appId 公众号appid
     * @param code
     * @return
     */
    PubAccountAuthToken getAccessTokenByAuthCode(String appId, String code);
}
