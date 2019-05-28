package me.own.learn.role.service.impl;

import me.own.learn.commons.base.dao.PageQueryResult;
import me.own.learn.role.dto.RoleDto;
import me.own.learn.role.service.RoleQueryCondition;
import me.own.learn.role.service.RoleService;
import me.own.learn.role.vo.RoleVo;
import org.springframework.stereotype.Service;

/**
 * @author yexudong
 * @date 2019/5/28 15:39
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public PageQueryResult<RoleVo> page(int pageNum, int pageSize, RoleQueryCondition condition) {
        return null;
    }

    @Override
    public RoleVo create(RoleDto roleDto) {
        return null;
    }

    @Override
    public RoleVo update(RoleDto roleDto) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
