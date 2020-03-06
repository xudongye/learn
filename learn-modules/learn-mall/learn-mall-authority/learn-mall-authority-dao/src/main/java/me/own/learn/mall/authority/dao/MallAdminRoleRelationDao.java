package me.own.learn.mall.authority.dao;

import me.own.commons.base.dao.BaseDao;
import me.own.learn.mall.authority.po.MallAdminRoleRelation;
import me.own.learn.mall.authority.po.MallRole;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/5 17:19
 */
public interface MallAdminRoleRelationDao extends BaseDao<MallAdminRoleRelation> {

    List<MallRole> getByAdminId(long adminId);
}
