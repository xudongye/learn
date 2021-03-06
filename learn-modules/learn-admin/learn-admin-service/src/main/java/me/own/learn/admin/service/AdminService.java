package me.own.learn.admin.service;

import me.own.learn.admin.dto.AdminDto;
import me.own.learn.admin.vo.AdminVo;
import me.own.commons.base.dao.PageQueryResult;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/5/28 14:17
 */
public interface AdminService {

    PageQueryResult<AdminVo> page(int pageNum, int pageSize, AdminQueryCondition condition);

    AdminVo create(AdminDto adminDto);

    AdminVo update(AdminDto adminDto);

    void delete(long id);

    void batchDelete(List<Long> adminIds);

    AdminVo bindRole(long adminId, long roleId);

    AdminVo getByLoginLabel(String loginLabel);

    AdminVo getById(long adminId);
}
