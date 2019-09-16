package me.own.learn.order.exception;

import me.own.commons.base.exception.BusinessException;

public class OrderAddressNotFoundException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "未找到收货地址！";
    }
}
