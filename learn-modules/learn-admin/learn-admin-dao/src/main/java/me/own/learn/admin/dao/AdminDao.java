package me.own.learn.admin.dao;

import me.own.learn.admin.po.Admin;
import me.own.learn.commons.base.dao.BaseDao;

/**
 * @author yexudong
 * @date 2019/5/28 14:13
 */
public interface AdminDao extends BaseDao<Admin> {

    Admin getByLoginLabel(String loginLabel);
}
