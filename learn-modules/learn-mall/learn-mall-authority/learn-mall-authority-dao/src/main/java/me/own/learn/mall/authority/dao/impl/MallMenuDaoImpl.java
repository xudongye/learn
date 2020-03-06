package me.own.learn.mall.authority.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.authority.dao.MallMenuDao;
import me.own.learn.mall.authority.po.MallMenu;
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
 * @Date: 2020/3/2 17:21
 */
@Repository
public class MallMenuDaoImpl extends BaseDaoImpl<MallMenu> implements MallMenuDao {
    @Override
    protected Class<MallMenu> getEntityClass() {
        return MallMenu.class;
    }

    @Override
    public List<MallMenu> getByAdminId(long adminId) {
        SQLQuery query = getCurrentSession().createSQLQuery("SELECT\n" +
                "\tmm.id,\n" +
                "\tmm.title,\n" +
                "\tmm.sort,\n" +
                "\tmm.parentId,\n" +
                "\tmm.`name`,\n" +
                "\tmm.`level`,\n" +
                "\tmm.icon,\n" +
                "\tmm.hidden,\n" +
                "\tmm.createTime \n" +
                "FROM\n" +
                "\tmall_admin_role_relation marr\n" +
                "\tLEFT JOIN mall_role mr ON marr.roleId = mr.id\n" +
                "\tLEFT JOIN mall_role_menu_relation mrmr ON mrmr.roleId = mr.id\n" +
                "\tLEFT JOIN mall_menu mm ON mm.id = mrmr.menuId \n" +
                "WHERE\n" +
                "\tmarr.adminId = :adminId \n" +
                "\tAND mm.id IS NOT NULL \n" +
                "GROUP BY\n" +
                "\tmm.id");
        query.setParameter("adminId", adminId);
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
