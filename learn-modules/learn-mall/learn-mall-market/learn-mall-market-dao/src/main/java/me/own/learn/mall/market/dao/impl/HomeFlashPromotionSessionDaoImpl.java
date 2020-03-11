package me.own.learn.mall.market.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.market.dao.HomeFlashPromotionSessionDao;
import me.own.learn.mall.market.po.HomeFlashPromotionSession;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/10 21:40
 */
@Repository
public class HomeFlashPromotionSessionDaoImpl extends BaseDaoImpl<HomeFlashPromotionSession> implements HomeFlashPromotionSessionDao {
    @Override
    protected Class<HomeFlashPromotionSession> getEntityClass() {
        return HomeFlashPromotionSession.class;
    }
}
