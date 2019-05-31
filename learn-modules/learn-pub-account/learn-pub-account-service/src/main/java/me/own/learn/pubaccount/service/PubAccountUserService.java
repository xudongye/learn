package me.own.learn.pubaccount.service;

import me.own.learn.pubaccount.service.model.UserInfoBo;

/**
 * @author Christopher.Wang 2017/3/3.
 */
public interface PubAccountUserService {
    UserInfoBo getUserInfo(String appId, String openId);
    UserInfoBo getUserInfoByToken(String token, String openId);
}
