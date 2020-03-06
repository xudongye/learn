package me.own.learn.mall.aythority.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.mall.aythority.vo.MallMenuVo;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/2 17:24
 */
public interface MallMenuService {

    PageQueryResult<MallMenuVo> page(int pageNum, int pageSize, long parentId);

    List<MallMenuVo> getByAdminId(long adminId);

    List<MallMenuVo> getByRoleId(long roleId);

    List<MallMenuVo> getMenus();
}
