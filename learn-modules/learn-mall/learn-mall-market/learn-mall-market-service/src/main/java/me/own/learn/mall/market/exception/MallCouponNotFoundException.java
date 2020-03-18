package me.own.learn.mall.market.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author: yexudong
 * @Date: 2020/3/13 15:22
 */
public class MallCouponNotFoundException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "未找到优惠券信息！";
    }
}
