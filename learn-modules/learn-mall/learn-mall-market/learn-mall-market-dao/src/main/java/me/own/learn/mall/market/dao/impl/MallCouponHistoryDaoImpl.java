package me.own.learn.mall.market.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.market.dao.MallCouponHistoryDao;
import me.own.learn.mall.market.po.MallCouponHistory;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/13 16:12
 */
@Repository
public class MallCouponHistoryDaoImpl extends BaseDaoImpl<MallCouponHistory> implements MallCouponHistoryDao {
    @Override
    protected Class<MallCouponHistory> getEntityClass() {
        return MallCouponHistory.class;
    }
}
