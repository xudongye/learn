package me.own.learn.role.dao.impl;

import me.own.learn.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.role.dao.RoleDao;
import me.own.learn.role.po.Role;
import org.springframework.stereotype.Repository;

/**
 * @author yexudong
 * @date 2019/5/28 15:17
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }
}
