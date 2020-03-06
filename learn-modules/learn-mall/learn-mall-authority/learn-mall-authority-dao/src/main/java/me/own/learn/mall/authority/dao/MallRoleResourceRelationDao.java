package me.own.learn.mall.authority.dao;

import me.own.commons.base.dao.BaseDao;
import me.own.learn.mall.authority.po.MallResource;
import me.own.learn.mall.authority.po.MallRoleResourceRelation;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/6 11:01
 */
public interface MallRoleResourceRelationDao extends BaseDao<MallRoleResourceRelation> {

    List<MallResource> getByRoleId(long roleId);
}
