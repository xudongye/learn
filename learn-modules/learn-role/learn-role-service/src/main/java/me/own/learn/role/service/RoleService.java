package me.own.learn.role.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.role.dto.PermissionDto;
import me.own.learn.role.dto.RoleDto;
import me.own.learn.role.vo.PermissionVo;
import me.own.learn.role.vo.RoleVo;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/5/28 15:27
 */
public interface RoleService {

    PageQueryResult<RoleVo> page(int pageNum, int pageSize, RoleQueryCondition condition);

    RoleVo getById(long roleId);

    RoleVo create(RoleDto roleDto);

    RoleVo update(RoleDto roleDto);

    void deleteRole(long roleId);

    RoleVo givePerm(long roleId, Long[] permIds);

    PermissionVo create(PermissionDto permissionDto);

    PermissionVo update(PermissionDto permissionDto);

    PermissionVo getPermById(long permId);

    void deletedPerm(long permId);

    List<Long> getPermIdsByRoleId(long roleId);
}
