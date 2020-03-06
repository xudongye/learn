package me.own.learn.mall.authority.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.authority.dao.MallRoleDao;
import me.own.learn.mall.authority.po.MallRole;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/5 16:33
 */
@Repository
public class MallRoleDaoImpl extends BaseDaoImpl<MallRole> implements MallRoleDao {
    @Override
    protected Class<MallRole> getEntityClass() {
        return MallRole.class;
    }
}
