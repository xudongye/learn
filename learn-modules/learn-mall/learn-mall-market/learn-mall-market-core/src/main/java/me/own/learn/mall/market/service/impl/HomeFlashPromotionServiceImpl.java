package me.own.learn.mall.market.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.exception.BusinessException;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.mall.market.dao.HomeFlashPromotionDao;
import me.own.learn.mall.market.dao.HomeFlashPromotionProductRelationDao;
import me.own.learn.mall.market.dao.HomeFlashPromotionSessionDao;
import me.own.learn.mall.market.po.HomeFlashPromotion;
import me.own.learn.mall.market.po.HomeFlashPromotionProductRelation;
import me.own.learn.mall.market.po.HomeFlashPromotionSession;
import me.own.learn.mall.market.service.HomeFlashPromotionService;
import me.own.learn.mall.market.vo.HomeFlashProductVo;
import me.own.learn.mall.market.vo.HomeFlashPromotionSessionVo;
import me.own.learn.mall.market.vo.HomeFlashPromotionVo;
import me.own.learn.mall.market.vo.MarketProductInfo;
import me.own.learn.mall.product.dto.NewProductDto;
import me.own.learn.mall.product.service.NewProductService;
import me.own.learn.mall.product.vo.ProductVo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/10 21:14
 */
@Service
public class HomeFlashPromotionServiceImpl implements HomeFlashPromotionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeFlashPromotionServiceImpl.class);

    @Autowired
    private HomeFlashPromotionDao promotionDao;

    @Autowired
    private HomeFlashPromotionSessionDao promotionSessionDao;

    @Autowired
    private HomeFlashPromotionProductRelationDao promotionProductRelationDao;

    @Autowired
    private NewProductService productService;

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<HomeFlashPromotionVo> page(int pageNum, int pageSize, String keyword) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(HomeFlashPromotion.class);
        if (keyword != null) {
            query.setSimpleCondition("title", keyword, QueryConstants.SimpleQueryMode.Like);
        }
        PageQueryResult<HomeFlashPromotion> result = promotionDao.pageQuery(pageNum, pageSize, query);
        return result.mapItems(HomeFlashPromotionVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HomeFlashPromotionSessionVo> listAllSession() {
        List<HomeFlashPromotionSession> sessions = promotionSessionDao.getAll(null, null);
        if (CollectionUtils.isNotEmpty(sessions)) {
            return Mapper.Default().mapArray(sessions, HomeFlashPromotionSessionVo.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HomeFlashPromotionSessionVo> listByPromotionId(long promotionId) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(HomeFlashPromotionSession.class);

        query.setSimpleCondition("status", 1 + "", QueryConstants.SimpleQueryMode.Equal);
        List<HomeFlashPromotionSession> sessions = promotionSessionDao.filter(query, null, null);
        if (CollectionUtils.isNotEmpty(sessions)) {
            List<HomeFlashPromotionSessionVo> sessionVos = Mapper.Default().mapArray(sessions, HomeFlashPromotionSessionVo.class);
            for (HomeFlashPromotionSessionVo sessionVo : sessionVos) {
                sessionVo.setProductCount(promotionProductRelationDao.getProductCount(promotionId, sessionVo.getId()));
            }
            return sessionVos;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<HomeFlashProductVo> pageFlashProductInfo(int pageNum, int pageSize, Long flashPromotionId, Long flashPromotionSessionId) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(HomeFlashPromotionProductRelation.class);
        query.setSimpleCondition("flashPromotionId", flashPromotionId + "", QueryConstants.SimpleQueryMode.Equal);
        query.setSimpleCondition("flashPromotionSessionId", flashPromotionSessionId + "", QueryConstants.SimpleQueryMode.Equal);
        PageQueryResult<HomeFlashPromotionProductRelation> result = promotionProductRelationDao.pageQuery(pageNum, pageSize, query);
        if (CollectionUtils.isNotEmpty(result.getItems())) {
            PageQueryResult<HomeFlashProductVo> pageQueryResult = result.mapItems(HomeFlashProductVo.class);
            for (HomeFlashProductVo item : pageQueryResult.getItems()) {
                try {
                    ProductVo productVo = productService.getById(item.getProductId());
                    item.setProduct(Mapper.Default().map(productVo, MarketProductInfo.class));
                } catch (BusinessException be) {
                    LOGGER.warn(be.getErrorMsg());
                }
            }
            return pageQueryResult;
        }
        return null;
    }

}
