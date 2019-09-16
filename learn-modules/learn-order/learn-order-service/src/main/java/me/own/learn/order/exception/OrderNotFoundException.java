package me.own.learn.order.exception;

import me.own.commons.base.exception.BusinessException;

public class OrderNotFoundException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "订单未找到!";
    }
}
