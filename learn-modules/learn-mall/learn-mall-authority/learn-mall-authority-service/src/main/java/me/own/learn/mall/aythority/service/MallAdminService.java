package me.own.learn.mall.aythority.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.mall.aythority.vo.MallAdminVo;

/**
 * @author: yexudong
 * @Date: 2020/3/5 16:00
 */
public interface MallAdminService {

    PageQueryResult<MallAdminVo> page(int pageNum, int pageSize, String keyword);

    MallAdminVo getById(long id);

    MallAdminVo getByUsername(String username);

}
