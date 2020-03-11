package me.own.learn.mall.market.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.dao.QueryOrder;
import me.own.commons.base.exception.BusinessException;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.mall.market.dao.HomeRecommendProductDao;
import me.own.learn.mall.market.po.HomeRecommendNewProduct;
import me.own.learn.mall.market.po.HomeRecommendProduct;
import me.own.learn.mall.market.service.HomeRecommendProductService;
import me.own.learn.mall.market.vo.HomeRecommendProductVo;
import me.own.learn.mall.market.vo.MarketProductInfo;
import me.own.learn.mall.product.service.NewProductService;
import me.own.learn.mall.product.vo.ProductVo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/10 16:45
 */
@Service
public class HomeRecommendProductServiceImpl implements HomeRecommendProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeRecommendProductServiceImpl.class);

    @Autowired
    private HomeRecommendProductDao recommendProductDao;

    @Autowired
    private NewProductService newProductService;

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

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<MarketProductInfo> listHotProduct() {
        QueryCriteriaUtil query = new QueryCriteriaUtil(HomeRecommendNewProduct.class);
        query.setSimpleCondition("recommendStatus", 1 + "", QueryConstants.SimpleQueryMode.Equal);
        List<HomeRecommendProduct> products = recommendProductDao.filter(query, null,
                new QueryOrder("sort", QueryOrder.DESC));
        if (CollectionUtils.isNotEmpty(products)) {
            List<MarketProductInfo> productInfos = new ArrayList<>();
            for (HomeRecommendProduct product : products) {
                try {
                    ProductVo productVo = newProductService.getById(product.getProductId());
                    productInfos.add(Mapper.Default().map(productVo, MarketProductInfo.class));
                } catch (BusinessException be) {
                    LOGGER.warn(be.getErrorMsg());
                }
            }
            return productInfos;
        }
        return null;
    }
}
