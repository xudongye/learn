package me.own.learn.order.exception;

import me.own.commons.base.exception.BusinessException;

public class UnfilledAddressException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "未填写收货地址！";
    }
}
