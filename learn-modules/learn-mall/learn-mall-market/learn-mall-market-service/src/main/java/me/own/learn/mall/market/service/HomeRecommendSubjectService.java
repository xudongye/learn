package me.own.learn.mall.market.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.mall.market.vo.HomeRecommendSubjectVo;
import me.own.learn.mall.market.vo.MallSubjectVo;
import me.own.learn.mall.market.vo.MarketSubjectInfo;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/10 16:20
 */
public interface HomeRecommendSubjectService {

    List<MarketSubjectInfo> listRecommendSubject();

    PageQueryResult<HomeRecommendSubjectVo> page(int pageNum, int pageSize, String subjectName, Integer recommendStatus);
}
