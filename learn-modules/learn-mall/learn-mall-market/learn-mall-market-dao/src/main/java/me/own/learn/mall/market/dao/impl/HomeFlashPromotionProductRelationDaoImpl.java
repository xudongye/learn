package me.own.learn.mall.market.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.market.dao.HomeFlashPromotionProductRelationDao;
import me.own.learn.mall.market.po.HomeFlashPromotionProductRelation;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/11 9:23
 */
@Repository
public class HomeFlashPromotionProductRelationDaoImpl extends BaseDaoImpl<HomeFlashPromotionProductRelation> implements HomeFlashPromotionProductRelationDao {
    @Override
    protected Class<HomeFlashPromotionProductRelation> getEntityClass() {
        return HomeFlashPromotionProductRelation.class;
    }

    @Override
    public Long getProductCount(long promotionId, long sessionId) {
        SQLQuery query = getCurrentSession().createSQLQuery("SELECT\n" +
                "\tCOUNT( * )  AS count \n" +
                "FROM\n" +
                "\thome_flash_promotion_product_relation \n" +
                "WHERE\n" +
                "\tflashPromotionId = :promotionId \n" +
                "\tAND flashPromotionSessionId = :sessionId");
        query.setParameter("promotionId", promotionId);
        query.setParameter("sessionId", sessionId);
        query.addScalar("count", StandardBasicTypes.LONG);
        return (Long) query.uniqueResult();
    }
}
