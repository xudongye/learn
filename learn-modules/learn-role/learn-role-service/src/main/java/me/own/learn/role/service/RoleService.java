package me.own.learn.role.service;

import me.own.learn.commons.base.dao.PageQueryResult;
import me.own.learn.role.dto.RoleDto;
import me.own.learn.role.vo.RoleVo;

/**
 * @author yexudong
 * @date 2019/5/28 15:27
 */
public interface RoleService {

    PageQueryResult<RoleVo> page(int pageNum, int pageSize, RoleQueryCondition condition);

    RoleVo create(RoleDto roleDto);

    RoleVo update(RoleDto roleDto);

    void delete(long id);
}
