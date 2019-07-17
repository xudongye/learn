package me.own.learn.role.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.role.dao.PermissionDao;
import me.own.learn.role.po.Permission;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<Long> getPermIdsByRoleId(long roleId) {
        SQLQuery query = getCurrentSession().createSQLQuery("SELECT\n" +
                "\tp.id \n" +
                "FROM\n" +
                "\troles_permissions rp\n" +
                "\tJOIN learn_permission p ON p.id = rp.permissionId \n" +
                "\tJOIN learn_role r ON r.id = rp.roleId \n" +
                "WHERE\n" +
                "\tp.deleted = FALSE \n" +
                "\tAND r.deleted = FALSE \n" +
                "\tAND r.id = :roleId");
        query.setParameter("roleId", roleId);
        query.addScalar("id", StandardBasicTypes.LONG);
        return query.list();
    }
}
