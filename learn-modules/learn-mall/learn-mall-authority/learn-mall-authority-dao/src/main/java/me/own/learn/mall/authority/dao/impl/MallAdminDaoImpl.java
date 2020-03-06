package me.own.learn.mall.authority.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.authority.dao.MallAdminDao;
import me.own.learn.mall.authority.po.MallAdmin;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/5 15:56
 */
@Repository
public class MallAdminDaoImpl extends BaseDaoImpl<MallAdmin> implements MallAdminDao {
    @Override
    protected Class<MallAdmin> getEntityClass() {
        return MallAdmin.class;
    }
}
