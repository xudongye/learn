package me.own.learn.mall.authority.dao;

import me.own.commons.base.dao.BaseDao;
import me.own.learn.mall.authority.po.MallMenu;
import me.own.learn.mall.authority.po.MallRoleMenuRelation;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/6 11:55
 */
public interface MallRoleMenuRelationDao extends BaseDao<MallRoleMenuRelation> {
    List<MallMenu> getByRoleId(long roleId);
}
