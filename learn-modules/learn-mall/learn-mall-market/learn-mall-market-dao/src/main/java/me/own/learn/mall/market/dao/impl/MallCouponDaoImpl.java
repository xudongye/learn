package me.own.learn.mall.market.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.market.dao.MallCouponDao;
import me.own.learn.mall.market.po.MallCoupon;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/12 10:09
 */
@Repository
public class MallCouponDaoImpl extends BaseDaoImpl<MallCoupon> implements MallCouponDao {
    @Override
    protected Class<MallCoupon> getEntityClass() {
        return MallCoupon.class;
    }
}
