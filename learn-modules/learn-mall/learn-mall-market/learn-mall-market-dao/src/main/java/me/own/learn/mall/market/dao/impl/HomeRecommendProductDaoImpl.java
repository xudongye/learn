package me.own.learn.mall.market.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.market.dao.HomeRecommendProductDao;
import me.own.learn.mall.market.po.HomeRecommendProduct;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/10 16:42
 */
@Repository
public class HomeRecommendProductDaoImpl extends BaseDaoImpl<HomeRecommendProduct> implements
        HomeRecommendProductDao {
    @Override
    protected Class<HomeRecommendProduct> getEntityClass() {
        return HomeRecommendProduct.class;
    }
}
