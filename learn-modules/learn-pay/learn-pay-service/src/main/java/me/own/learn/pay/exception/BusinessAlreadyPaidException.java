package me.own.learn.pay.exception;

import me.own.commons.base.exception.BusinessException;

public class BusinessAlreadyPaidException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "订单已经支付!";
    }
}
