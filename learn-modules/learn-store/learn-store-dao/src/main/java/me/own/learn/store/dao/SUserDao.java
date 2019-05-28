package me.own.learn.store.dao;

import me.own.learn.commons.base.dao.BaseDao;
import me.own.learn.store.po.SUser;

/**
 * @author yexudong
 * @date 2019/5/15 14:24
 */
public interface SUserDao extends BaseDao<SUser> {

    SUser getByUsernameAndPassword(String username, String password);
}
