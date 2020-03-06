package me.own.learn.mall.aythority.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.mall.aythority.vo.MallResourceVo;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/6 10:50
 */
public interface MallResourceService {
    List<MallResourceVo> getByRoleId(long roleId);

    PageQueryResult<MallResourceVo> page(int pageNum, int pageSize, Long categoryId, String nameKw, String urlKw);
}
