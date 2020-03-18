package me.own.learn.mall.market.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.mall.market.vo.MallCouponHistoryVo;
import me.own.learn.mall.market.vo.MallCouponVo;

/**
 * @author: yexudong
 * @Date: 2020/3/12 10:10
 */
public interface MallCouponService {

    PageQueryResult<MallCouponVo> page(int pageNum, int pageSize, String name, Integer type);

    MallCouponVo getById(Long id);

    PageQueryResult<MallCouponHistoryVo> pageByCouponId(int pageNum, int pageSize, Long couponId);
}
