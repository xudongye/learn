package me.own.learn.mall.market.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.mall.market.vo.*;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/10 21:09
 */
public interface HomeFlashPromotionService {

    MarketFlashInfo getHomePromotion();

    List<MarketProductInfo> getAll(Long flashPromotionId, Long flashPromotionSessionId);

    PageQueryResult<HomeFlashPromotionVo> page(int pageNum, int pageSize, String keyword);

    List<HomeFlashPromotionSessionVo> listAllSession();

    /**
     * 获取全部可选场次及其数量
     *
     * @param promotionId
     * @return
     */
    List<HomeFlashPromotionSessionVo> listByPromotionId(long promotionId);

    /**
     * 分页查询不同场次关联及商品信息
     *
     * @param pageNum
     * @param pageSize
     * @param flashPromotionId
     * @param flashPromotionSessionId
     * @return
     */
    PageQueryResult<HomeFlashProductVo> pageFlashProductInfo(int pageNum, int pageSize, Long flashPromotionId, Long flashPromotionSessionId);
}
