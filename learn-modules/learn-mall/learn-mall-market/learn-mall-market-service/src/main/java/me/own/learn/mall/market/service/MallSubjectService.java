package me.own.learn.mall.market.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.mall.market.vo.MallSubjectVo;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/9 16:54
 */
public interface MallSubjectService {

    List<MallSubjectVo> listAll();

    PageQueryResult<MallSubjectVo> page(int pageNum, int pageSize, String keyword);
}
