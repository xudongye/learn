package me.own.learn.mall.market.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.learn.mall.market.dao.HomeRecommendProductDao;
import me.own.learn.mall.market.po.HomeRecommendProduct;
import me.own.learn.mall.market.service.HomeRecommendProductService;
import me.own.learn.mall.market.vo.HomeRecommendProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: yexudong
 * @Date: 2020/3/10 16:45
 */
@Service
public class HomeRecommendProductServiceImpl implements HomeRecommendProductService {

    @Autowired
    private HomeRecommendProductDao recommendProductDao;

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<HomeRecommendProductVo> page(int pageNum, int pageSize, String productName, Integer recommendStatus) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(HomeRecommendProduct.class);

        if (productName != null) {
            query.setSimpleCondition("productName", productName, QueryConstants.SimpleQueryMode.Like);
        }
        if (recommendStatus != null) {
            query.setSimpleCondition("recommendStatus", recommendStatus + "", QueryConstants.SimpleQueryMode.Equal);
        }
        PageQueryResult<HomeRecommendProduct> result = recommendProductDao.pageQuery(pageNum, pageSize, query);
        return result.mapItems(HomeRecommendProductVo.class);
    }
}
