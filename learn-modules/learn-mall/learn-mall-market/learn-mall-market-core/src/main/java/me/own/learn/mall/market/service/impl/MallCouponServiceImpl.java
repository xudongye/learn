package me.own.learn.mall.market.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.mall.market.dao.MallCouponDao;
import me.own.learn.mall.market.dao.MallCouponHistoryDao;
import me.own.learn.mall.market.exception.MallCouponNotFoundException;
import me.own.learn.mall.market.po.MallCoupon;
import me.own.learn.mall.market.po.MallCouponHistory;
import me.own.learn.mall.market.service.MallCouponService;
import me.own.learn.mall.market.vo.MallCouponHistoryVo;
import me.own.learn.mall.market.vo.MallCouponVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: yexudong
 * @Date: 2020/3/12 10:13
 */
@Service
public class MallCouponServiceImpl implements MallCouponService {

    @Autowired
    private MallCouponDao couponDao;
    @Autowired
    private MallCouponHistoryDao couponHistoryDao;

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<MallCouponVo> page(int pageNum, int pageSize, String name, Integer type) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(MallCoupon.class);
        if (name != null) {
            query.setSimpleCondition("name", name, QueryConstants.SimpleQueryMode.Like);
        }
        if (type != null) {
            query.setSimpleCondition("type", type + "", QueryConstants.SimpleQueryMode.Equal);
        }
        PageQueryResult<MallCoupon> result = couponDao.pageQuery(pageNum, pageSize, query);
        return result.mapItems(MallCouponVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public MallCouponVo getById(Long id) {
        MallCoupon coupon = couponDao.get(id);
        if (coupon == null) {
            throw new MallCouponNotFoundException();
        }
        return Mapper.Default().map(coupon, MallCouponVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<MallCouponHistoryVo> pageByCouponId(int pageNum, int pageSize, Long couponId) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(MallCouponHistory.class);
        if (couponId != null) {
            query.setSimpleCondition("couponId", couponId + "", QueryConstants.SimpleQueryMode.Equal);
        }
        PageQueryResult<MallCouponHistory> result = couponHistoryDao.pageQuery(pageNum, pageSize, query);
        return result.mapItems(MallCouponHistoryVo.class);
    }
}
