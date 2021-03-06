package me.own.learn.role.dao;

import me.own.commons.base.dao.BaseDao;
import me.own.learn.role.po.Permission;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/5/28 15:22
 */
public interface PermissionDao extends BaseDao<Permission> {

    List<Long> getPermIdsByRoleId(long roleId);
}
