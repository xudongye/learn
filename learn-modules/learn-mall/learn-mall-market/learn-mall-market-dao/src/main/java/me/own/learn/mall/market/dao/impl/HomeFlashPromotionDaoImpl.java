package me.own.learn.mall.market.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.market.dao.HomeFlashPromotionDao;
import me.own.learn.mall.market.po.HomeFlashPromotion;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/10 18:13
 */
@Repository
public class HomeFlashPromotionDaoImpl extends BaseDaoImpl<HomeFlashPromotion> implements HomeFlashPromotionDao {
    @Override
    protected Class<HomeFlashPromotion> getEntityClass() {
        return HomeFlashPromotion.class;
    }
}
