package me.own.learn.order.exception;

import me.own.commons.base.exception.BusinessException;

public class OrderDetailNotNullException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "详情数据未提交！";
    }
}
