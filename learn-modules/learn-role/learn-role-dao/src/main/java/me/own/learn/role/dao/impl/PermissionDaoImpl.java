package me.own.learn.role.dao.impl;

import me.own.learn.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.role.dao.PermissionDao;
import me.own.learn.role.po.Permission;
import org.springframework.stereotype.Repository;

/**
 * @author yexudong
 * @date 2019/5/28 15:22
 */
@Repository
public class PermissionDaoImpl extends BaseDaoImpl<Permission> implements PermissionDao {
    @Override
    protected Class<Permission> getEntityClass() {
        return Permission.class;
    }
}
