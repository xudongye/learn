package me.own.learn.authorization.dao.impl;

import me.own.learn.authorization.dao.TokenDao;
import me.own.learn.authorization.po.Token;
import me.own.learn.commons.base.dao.impl.BaseRedisJsonHashDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * @author yexudong
 * @date 2019/5/30 9:37
 */
@Repository
public class TokenDaoImpl extends BaseRedisJsonHashDaoImpl<String, Token> implements TokenDao {

    private static final String TOKEN_HISTORY_KEY = "learn:token:value:{{value}}";

    @Override
    public Token getByValue(String value) {
        return super.getEntity(value);
    }

    @Override
    public void create(Token adminToken) {
        super.setEntity(adminToken.getValue(), adminToken);
        super.expire(adminToken.getValue(), 7, TimeUnit.DAYS);
    }

    @Override
    public void update(Token adminToken) {
        super.setEntity(adminToken.getValue(), adminToken);
    }

    @Override
    protected Class<Token> getEntityClass() {
        return Token.class;
    }

    @Override
    protected String convertKey(String key) {
        return concatKeyByTokenValue(key);
    }

    private static String concatKeyByTokenValue(String value) {
        return TOKEN_HISTORY_KEY.replace("{{value}}", value);
    }
}
