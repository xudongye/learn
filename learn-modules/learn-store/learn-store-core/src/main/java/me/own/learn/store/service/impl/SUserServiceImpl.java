package me.own.learn.store.service.impl;

import me.own.learn.commons.base.dao.QueryConstants;
import me.own.learn.commons.base.dao.QueryCriteriaUtil;
import me.own.learn.commons.base.utils.encode.MD5;
import me.own.learn.commons.base.utils.mapper.Mapper;
import me.own.learn.store.bo.SUserBo;
import me.own.learn.store.bo.SUserLoginBo;
import me.own.learn.store.dao.SUserDao;
import me.own.learn.store.exception.SUserNotFoundException;
import me.own.learn.store.exception.SUsernameExistException;
import me.own.learn.store.po.SUser;
import me.own.learn.store.service.SUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author yexudong
 * @date 2019/5/15 14:35
 */
@Service
public class SUserServiceImpl implements SUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SUserServiceImpl.class);

    @Autowired
    private SUserDao userDao;

    @Override
    @Transactional
    public SUserBo createFromRegister(SUserBo userBo) {
        if (nameExist(userBo.getUsername())) {
            throw new SUsernameExistException();
        }
        SUser user = Mapper.Default().map(userBo, SUser.class);
        user.setCreateTime(new Date());
        user.setPassword(MD5.getMD5Code(userBo.getPassword()));
        userDao.create(user);
        LOGGER.info("create new user {}", user.getUsername());
        return Mapper.Default().map(user, SUserBo.class);
    }

    private boolean nameExist(String username) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(SUser.class);
        query.setSimpleCondition("username", username, QueryConstants.SimpleQueryMode.Equal);
        return userDao.getCount(query) > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public SUserBo getWhenUserLogin(SUserLoginBo loginBo) {
        SUser sUser = userDao.getByUsernameAndPassword(loginBo.getUsername(), loginBo.getPassword());
        if (sUser == null) {
            throw new SUserNotFoundException();
        }
        return Mapper.Default().map(sUser, SUserBo.class);
    }
}
