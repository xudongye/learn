package me.own.learn.mall.market.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.dao.QueryOrder;
import me.own.commons.base.exception.BusinessException;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.mall.market.dao.HomeBrandDao;
import me.own.learn.mall.market.po.HomeBrand;
import me.own.learn.mall.market.service.HomeBrandService;
import me.own.learn.mall.market.vo.HomeBrandVo;
import me.own.learn.mall.market.vo.MarketBrandInfo;
import me.own.learn.mall.product.service.ProductBrandService;
import me.own.learn.mall.product.vo.ProductBrandVo;
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
 * @Date: 2020/3/10 17:32
 */
@Service
public class HomeBrandServiceImpl implements HomeBrandService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeBrandServiceImpl.class);

    @Autowired
    private HomeBrandDao brandDao;

    @Autowired
    private ProductBrandService productBrandService;

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<HomeBrandVo> page(int pageNum, int pageSize, String brandName, Integer recommendStatus) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(HomeBrand.class);

        if (brandName != null) {
            query.setSimpleCondition("brandName", brandName, QueryConstants.SimpleQueryMode.Like);
        }
        if (recommendStatus != null) {
            query.setSimpleCondition("recommendStatus", recommendStatus + "", QueryConstants.SimpleQueryMode.Equal);
        }
        PageQueryResult<HomeBrand> result = brandDao.pageQuery(pageNum, pageSize, query);
        return result.mapItems(HomeBrandVo.class);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<MarketBrandInfo> listRecommendBrand() {
        QueryCriteriaUtil query = new QueryCriteriaUtil(HomeBrand.class);
        query.setSimpleCondition("recommendStatus", 1 + "", QueryConstants.SimpleQueryMode.Equal);
        List<HomeBrand> brands = brandDao.filter(query, null,
                new QueryOrder("sort", QueryOrder.DESC));
        if (CollectionUtils.isNotEmpty(brands)) {
            List<MarketBrandInfo> brandInfos = new ArrayList<>();
            for (HomeBrand brand : brands) {
                try {
                    ProductBrandVo productBrandVo = productBrandService.getById(brand.getBrandId());
                    brandInfos.add(Mapper.Default().map(productBrandVo, MarketBrandInfo.class));
                } catch (BusinessException be) {
                    LOGGER.warn(be.getErrorMsg());
                }

            }
            return brandInfos;
        }
        return null;
    }
}
