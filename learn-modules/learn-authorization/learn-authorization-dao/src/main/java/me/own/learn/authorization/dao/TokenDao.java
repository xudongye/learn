package me.own.learn.authorization.dao;

import me.own.learn.authorization.po.Token;

/**
 * @author yexudong
 * @date 2019/5/30 9:35
 */
public interface TokenDao {

    Token getByValue(String value);

    void create(Token adminToken);

    void delete(String value);

    void update(Token adminToken);
}
