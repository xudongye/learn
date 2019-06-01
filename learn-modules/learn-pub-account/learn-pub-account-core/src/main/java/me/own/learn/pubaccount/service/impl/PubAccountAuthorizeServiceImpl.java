package me.own.learn.pubaccount.service.impl;

import me.own.commons.base.utils.mapper.Mapper;
import me.own.commons.wechat.pubaccount.accesstoken.AuthorizationCodeService;
import me.own.commons.wechat.pubaccount.accesstoken.impl.AuthorizationCodeServiceImpl;
import me.own.learn.pubaccount.service.PubAccountAuthorizeService;
import me.own.learn.pubaccount.service.model.PubAccountAuthToken;
import org.springframework.stereotype.Service;

/**
 * Created by CN-LEBOOK-YANGLI on 2017/3/16.
 */

@Service
public class PubAccountAuthorizeServiceImpl implements PubAccountAuthorizeService {
    private AuthorizationCodeService authorizationCodeService = new AuthorizationCodeServiceImpl();

    @Override
    public PubAccountAuthToken getAccessTokenByAuthCode(String appId, String code) {
        me.own.commons.wechat.pubaccount.accesstoken.model.PubAccountAuthToken authToken = authorizationCodeService.getAccessTokenByAuthCode(appId, code);
        return Mapper.Default().map(authToken, PubAccountAuthToken.class);
    }
}
