package me.own.learn.mall.market.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.mall.market.vo.HomeRecommendSubjectVo;

/**
 * @author: yexudong
 * @Date: 2020/3/10 16:20
 */
public interface HomeRecommendSubjectService {

    PageQueryResult<HomeRecommendSubjectVo> page(int pageNum, int pageSize, String subjectName, Integer recommendStatus);
}
