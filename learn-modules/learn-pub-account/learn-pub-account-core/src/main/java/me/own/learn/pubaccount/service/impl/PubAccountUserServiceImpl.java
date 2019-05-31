package me.own.learn.pubaccount.service.impl;

import me.own.learn.commons.base.utils.mapper.Mapper;
import me.own.learn.pubaccount.service.PubAccountUserService;
import me.own.learn.pubaccount.service.model.UserInfoBo;
import org.springframework.stereotype.Component;

@Component
public class PubAccountUserServiceImpl implements PubAccountUserService {
    private me.own.commons.wechat.pubaccount.user.UserService sdkUserService = new me.own.commons.wechat.pubaccount.user.impl.UserServiceImpl();

    @Override
    public UserInfoBo getUserInfo(String appId, String openId) {
        return Mapper.Default().map(sdkUserService.getUserInfo(appId, openId), UserInfoBo.class);
    }

    @Override
    public UserInfoBo getUserInfoByToken(String token, String openId) {
        return Mapper.Default().map(sdkUserService.getUserInfo(token, openId), UserInfoBo.class);
    }
}
