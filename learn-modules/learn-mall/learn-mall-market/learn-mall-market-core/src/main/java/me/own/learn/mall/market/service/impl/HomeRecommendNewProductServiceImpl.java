package me.own.learn.mall.market.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.learn.mall.market.dao.HomeRecommendNewProductDao;
import me.own.learn.mall.market.po.HomeRecommendNewProduct;
import me.own.learn.mall.market.po.HomeRecommendProduct;
import me.own.learn.mall.market.service.HomeRecommendNewProductService;
import me.own.learn.mall.market.vo.HomeRecommendNewProductVo;
import me.own.learn.mall.market.vo.HomeRecommendProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: yexudong
 * @Date: 2020/3/10 17:11
 */
@Service
public class HomeRecommendNewProductServiceImpl implements HomeRecommendNewProductService {

    @Autowired
    private HomeRecommendNewProductDao newProductDao;

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<HomeRecommendNewProductVo> page(int pageNum, int pageSize, String productName, Integer recommendStatus) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(HomeRecommendNewProduct.class);

        if (productName != null) {
            query.setSimpleCondition("productName", productName, QueryConstants.SimpleQueryMode.Like);
        }
        if (recommendStatus != null) {
            query.setSimpleCondition("recommendStatus", recommendStatus + "", QueryConstants.SimpleQueryMode.Equal);
        }
        PageQueryResult<HomeRecommendNewProduct> result = newProductDao.pageQuery(pageNum, pageSize, query);
        return result.mapItems(HomeRecommendNewProductVo.class);
    }
}
