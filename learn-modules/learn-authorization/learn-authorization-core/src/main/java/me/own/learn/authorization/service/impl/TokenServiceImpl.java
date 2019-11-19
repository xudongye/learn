package me.own.learn.authorization.service.impl;

import me.own.learn.authorization.dao.TokenDao;
import me.own.learn.authorization.po.Token;
import me.own.learn.authorization.service.TokenService;
import me.own.learn.authorization.utils.TokenGenerator;
import me.own.learn.authorization.vo.TokenVo;
import me.own.commons.base.utils.mapper.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * @author yexudong
 * @date 2019/5/30 10:06
 */
@Service
public class TokenServiceImpl implements TokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

    public static final long EXPIRY_INTERVAL = 30 * 60 * 1000;

    @Autowired
    private TokenDao tokenDao;

    @Override
    public TokenVo createFromCustomer(long customerId) {
        String tokenValue = TokenGenerator.getInstance().generateTokenCode("CSTM" + customerId);
        Token newToken = new Token(customerId, tokenValue);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 7);
        newToken.setTimestamp(c.getTimeInMillis());
        tokenDao.create(newToken);
        LOGGER.debug("create new token " + tokenValue + " for customer " + customerId);
        return Mapper.Default().map(newToken, TokenVo.class);
    }

    @Override
    public TokenVo createFromAdmin(long adminId, String name) {
        String token = TokenGenerator.getInstance().generateTokenCode("ADMIN" + name);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);
        Token adminToken = new Token(adminId, token, calendar.getTimeInMillis());
        tokenDao.create(adminToken);
        LOGGER.info("create new token {} for admin {} when login.", token, adminId);
        return Mapper.Default().map(adminToken, TokenVo.class);
    }

    @Override
    public void remove(String value) {
        tokenDao.delete(value);
    }

    @Override
    public TokenVo getByTokenValue(String tokenValue) {
        Token token = tokenDao.getByValue(tokenValue);
        if (null != token) {
            return Mapper.Default().map(token, TokenVo.class);
        }
        return null;
    }

    @Override
    public boolean isConsistent(String value) {
        Token token = tokenDao.getByValue(value);
        if (null != token) {
            String realToken = token.getValue();
            if (realToken.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isExpired(String value) {
        Token token = tokenDao.getByValue(value);
        if (null != token) {
            if (token.getTimestamp() == null || System.currentTimeMillis() - token.getTimestamp() <= EXPIRY_INTERVAL) {
                return false;
            }
        }
        return true;
    }
}
