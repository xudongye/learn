package me.own.learn.user.dao.impl;

import me.own.learn.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.user.dao.UserDao;
import me.own.learn.user.po.User;
import org.springframework.stereotype.Repository;

/**
 * @author yexudong
 * @date 2019/4/16 17:10
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
