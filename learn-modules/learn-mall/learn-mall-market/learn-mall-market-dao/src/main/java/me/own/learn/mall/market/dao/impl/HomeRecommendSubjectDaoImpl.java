package me.own.learn.mall.market.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.market.dao.HomeRecommendSubjectDao;
import me.own.learn.mall.market.po.HomeRecommendSubject;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/10 16:19
 */
@Repository
public class HomeRecommendSubjectDaoImpl extends BaseDaoImpl<HomeRecommendSubject> implements HomeRecommendSubjectDao {
    @Override
    protected Class<HomeRecommendSubject> getEntityClass() {
        return HomeRecommendSubject.class;
    }
}
