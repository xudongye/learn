package me.own.learn.pay.exception;

import me.own.commons.base.exception.BusinessException;

public class InvalidOutTradeNoPrefixException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "invalid business key received";
    }
}
