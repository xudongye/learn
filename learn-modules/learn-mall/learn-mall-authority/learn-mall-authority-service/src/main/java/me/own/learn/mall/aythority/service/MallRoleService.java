package me.own.learn.mall.aythority.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.mall.aythority.vo.MallRoleVo;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/5 16:34
 */
public interface MallRoleService {

    List<MallRoleVo> listAll();

    List<MallRoleVo> getByAdminId(long id);

    PageQueryResult<MallRoleVo> page(int pageNum, int pageSize, String keyword);
}
