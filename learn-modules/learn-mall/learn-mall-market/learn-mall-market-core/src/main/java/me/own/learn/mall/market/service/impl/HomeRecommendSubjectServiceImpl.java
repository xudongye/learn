package me.own.learn.mall.market.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.mall.market.dao.HomeRecommendSubjectDao;
import me.own.learn.mall.market.dao.MallSubjectDao;
import me.own.learn.mall.market.po.HomeRecommendSubject;
import me.own.learn.mall.market.po.MallSubject;
import me.own.learn.mall.market.service.HomeRecommendSubjectService;
import me.own.learn.mall.market.vo.HomeRecommendSubjectVo;
import me.own.learn.mall.market.vo.MallSubjectVo;
import me.own.learn.mall.market.vo.MarketSubjectInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/10 16:22
 */
@Service
public class HomeRecommendSubjectServiceImpl implements HomeRecommendSubjectService {

    @Autowired
    private HomeRecommendSubjectDao recommendSubjectDao;

    @Autowired
    private MallSubjectDao subjectDao;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<MarketSubjectInfo> listRecommendSubject() {
        QueryCriteriaUtil query = new QueryCriteriaUtil(MallSubject.class);
        query.setSimpleCondition("recommendStatus", 1 + "", QueryConstants.SimpleQueryMode.Equal);
        List<HomeRecommendSubject> recommendSubjects = recommendSubjectDao.filter(query, null, null);
        if (CollectionUtils.isNotEmpty(recommendSubjects)) {
            List<MallSubjectVo> subjects = new ArrayList<>();
            for (HomeRecommendSubject recommendSubject : recommendSubjects) {
                MallSubject subject = subjectDao.get(recommendSubject.getSubjectId());
                if (subject != null) {
                    subjects.add(Mapper.Default().map(subject, MallSubjectVo.class));
                }
            }
            return Mapper.Default().mapArray(subjects, MarketSubjectInfo.class);
        }
        return null;
    }

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
