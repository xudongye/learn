package me.own.learn.authorization.service;

import me.own.learn.authorization.vo.TokenVo;

/**
 * @author yexudong
 * @date 2019/5/30 9:43
 */
public interface TokenService {

    TokenVo createFromCustomer(long customerId);

    TokenVo createFromAdmin(long adminId, String name);

    void remove(String value);

    TokenVo getByTokenValue(String tokenValue);

    boolean isConsistent(String value);

    boolean isExpired(String value);
}
