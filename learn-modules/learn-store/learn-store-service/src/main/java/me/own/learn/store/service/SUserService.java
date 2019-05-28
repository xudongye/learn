package me.own.learn.store.service;

import me.own.learn.store.bo.SUserBo;
import me.own.learn.store.bo.SUserLoginBo;

/**
 * @author yexudong
 * @date 2019/5/15 14:28
 */
public interface SUserService {

    /**
     * 注册管理员
     *
     * @param userBo
     * @return
     */
    SUserBo createFromRegister(SUserBo userBo);

    /**
     * 登录时获取user
     *
     * @param loginBo
     * @return
     */
    SUserBo getWhenUserLogin(SUserLoginBo loginBo);
}
