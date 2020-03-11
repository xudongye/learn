package me.own.learn.mall.market.dao;

import me.own.commons.base.dao.BaseDao;
import me.own.learn.mall.market.po.HomeFlashPromotionProductRelation;

/**
 * @author: yexudong
 * @Date: 2020/3/11 9:22
 */
public interface HomeFlashPromotionProductRelationDao extends BaseDao<HomeFlashPromotionProductRelation> {

    Long getProductCount(long promotionId, long sessionId);
}
