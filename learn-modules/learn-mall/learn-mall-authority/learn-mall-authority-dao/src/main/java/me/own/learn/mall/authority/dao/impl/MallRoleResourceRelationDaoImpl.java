package me.own.learn.mall.authority.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.authority.dao.MallRoleResourceRelationDao;
import me.own.learn.mall.authority.po.MallResource;
import me.own.learn.mall.authority.po.MallRole;
import me.own.learn.mall.authority.po.MallRoleResourceRelation;
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
 * @Date: 2020/3/6 11:01
 */
@Repository
public class MallRoleResourceRelationDaoImpl extends BaseDaoImpl<MallRoleResourceRelation> implements MallRoleResourceRelationDao {
    @Override
    protected Class<MallRoleResourceRelation> getEntityClass() {
        return MallRoleResourceRelation.class;
    }

    @Override
    public List<MallResource> getByRoleId(long roleId) {
        SQLQuery query = getCurrentSession().createSQLQuery("SELECT\n" +
                "\tmr.id,\n" +
                "\tmr.createTime,\n" +
                "\tmr.`name`,\n" +
                "\tmr.url,\n" +
                "\tmr.description,\n" +
                "\tmr.categoryId \n" +
                "FROM\n" +
                "\tmall_resource mr\n" +
                "\tLEFT JOIN mall_role_resource_relation mrrr ON mr.id = mrrr.resourceId \n" +
                "WHERE\n" +
                "\tmrrr.roleId = :roleId");
        query.setParameter("roleId", roleId);
        query.setResultTransformer(Transformers.aliasToBean(MallResource.class));
        query.addScalar("id", LongType.INSTANCE)
                .addScalar("name", StringType.INSTANCE)
                .addScalar("description", StringType.INSTANCE)
                .addScalar("url", StringType.INSTANCE)
                .addScalar("createTime", DateType.INSTANCE)
                .addScalar("categoryId", LongType.INSTANCE);
        return query.list();
    }
}
