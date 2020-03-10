package me.own.learn.mall.market.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author: yexudong
 * @Date: 2020/3/10 15:54
 */
public class MallAdvertiseNotFoundException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "广告信息不存在！";
    }
}
