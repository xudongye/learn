package me.own.learn.mall.authority.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.authority.dao.MallAdminRoleRelationDao;
import me.own.learn.mall.authority.po.MallAdminRoleRelation;
import me.own.learn.mall.authority.po.MallRole;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/5 17:19
 */
@Repository
public class MallAdminRoleRelationDaoImpl extends BaseDaoImpl<MallAdminRoleRelation> implements MallAdminRoleRelationDao {
    @Override
    protected Class<MallAdminRoleRelation> getEntityClass() {
        return MallAdminRoleRelation.class;
    }

    @Override
    public List<MallRole> getByAdminId(long adminId) {
        SQLQuery query = getCurrentSession().createSQLQuery("SELECT\n" +
                "\tmr.id,\n" +
                "\tmr.`name`,\n" +
                "\tmr.description,\n" +
                "\tmr.adminCount,\n" +
                "\tmr.createTime,\n" +
                "\tmr.sort,\n" +
                "\tmr.`status` \n" +
                "FROM\n" +
                "\tmall_role mr\n" +
                "\tLEFT JOIN mall_admin_role_relation marr ON marr.roleId = mr.id \n" +
                "WHERE\n" +
                "\tmarr.adminId = :adminId");
        query.setParameter("adminId", adminId);
        query.setResultTransformer(Transformers.aliasToBean(MallRole.class));
        query.addScalar("id", LongType.INSTANCE)
                .addScalar("name", StringType.INSTANCE)
                .addScalar("description", StringType.INSTANCE)
                .addScalar("adminCount", IntegerType.INSTANCE)
                .addScalar("createTime", DateType.INSTANCE)
                .addScalar("sort", IntegerType.INSTANCE)
                .addScalar("status", IntegerType.INSTANCE);
        return query.list();
    }
}
