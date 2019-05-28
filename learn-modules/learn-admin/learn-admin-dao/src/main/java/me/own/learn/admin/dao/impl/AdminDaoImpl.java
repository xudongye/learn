package me.own.learn.admin.dao.impl;

import me.own.learn.admin.dao.AdminDao;
import me.own.learn.admin.po.Admin;
import me.own.learn.commons.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * @author yexudong
 * @date 2019/5/28 14:14
 */
@Repository
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao {
    @Override
    protected Class<Admin> getEntityClass() {
        return Admin.class;
    }
}
