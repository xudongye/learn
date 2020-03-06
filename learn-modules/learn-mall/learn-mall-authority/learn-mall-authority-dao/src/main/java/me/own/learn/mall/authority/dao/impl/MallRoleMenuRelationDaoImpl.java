package me.own.learn.mall.authority.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.authority.dao.MallRoleMenuRelationDao;
import me.own.learn.mall.authority.po.MallMenu;
import me.own.learn.mall.authority.po.MallRoleMenuRelation;
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
 * @Date: 2020/3/6 11:55
 */
@Repository
public class MallRoleMenuRelationDaoImpl extends BaseDaoImpl<MallRoleMenuRelation> implements MallRoleMenuRelationDao {
    @Override
    protected Class<MallRoleMenuRelation> getEntityClass() {
        return MallRoleMenuRelation.class;
    }

    @Override
    public List<MallMenu> getByRoleId(long roleId) {
        SQLQuery query = getCurrentSession().createSQLQuery("SELECT\n" +
                "\tmm.id,\n" +
                "\tmm.parentId,\n" +
                "\tmm.createTime,\n" +
                "\tmm.title,\n" +
                "\tmm.`level`,\n" +
                "\tmm.sort,\n" +
                "\tmm.`name`,\n" +
                "\tmm.icon,\n" +
                "\tmm.hidden \n" +
                "FROM\n" +
                "\tmall_menu mm\n" +
                "\tLEFT JOIN mall_role_menu_relation mrmr ON mm.id = mrmr.menuId \n" +
                "WHERE\n" +
                "\tmrmr.roleId = :roleId");
        query.setParameter("roleId", roleId);
        query.setResultTransformer(Transformers.aliasToBean(MallMenu.class));
        query.addScalar("id", LongType.INSTANCE)
                .addScalar("name", StringType.INSTANCE)
                .addScalar("parentId", LongType.INSTANCE)
                .addScalar("title", StringType.INSTANCE)
                .addScalar("createTime", DateType.INSTANCE)
                .addScalar("level", IntegerType.INSTANCE)
                .addScalar("sort", IntegerType.INSTANCE)
                .addScalar("icon", StringType.INSTANCE)
                .addScalar("hidden", IntegerType.INSTANCE);
        return query.list();
    }
}
