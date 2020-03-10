package me.own.learn.mall.market.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.learn.mall.market.dao.HomeRecommendSubjectDao;
import me.own.learn.mall.market.po.HomeRecommendSubject;
import me.own.learn.mall.market.service.HomeRecommendSubjectService;
import me.own.learn.mall.market.vo.HomeRecommendSubjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: yexudong
 * @Date: 2020/3/10 16:22
 */
@Service
public class HomeRecommendSubjectServiceImpl implements HomeRecommendSubjectService {

    @Autowired
    private HomeRecommendSubjectDao recommendSubjectDao;

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<HomeRecommendSubjectVo> page(int pageNum, int pageSize, String subjectName, Integer recommendStatus) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(HomeRecommendSubject.class);

        if (subjectName != null) {
            query.setSimpleCondition("subjectName", subjectName, QueryConstants.SimpleQueryMode.Like);
        }
        if (recommendStatus != null) {
            query.setSimpleCondition("recommendStatus", recommendStatus + "", QueryConstants.SimpleQueryMode.Equal);
        }
        PageQueryResult<HomeRecommendSubject> result = recommendSubjectDao.pageQuery(pageNum, pageSize, query);
        return result.mapItems(HomeRecommendSubjectVo.class);
    }
}
