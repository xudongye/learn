package me.own.learn.mall.market.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.market.dao.HomeRecommendNewProductDao;
import me.own.learn.mall.market.po.HomeRecommendNewProduct;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/10 17:10
 */
@Repository
public class HomeRecommendNewProductDaoImpl extends BaseDaoImpl<HomeRecommendNewProduct> implements HomeRecommendNewProductDao {
    @Override
    protected Class<HomeRecommendNewProduct> getEntityClass() {
        return HomeRecommendNewProduct.class;
    }
}
