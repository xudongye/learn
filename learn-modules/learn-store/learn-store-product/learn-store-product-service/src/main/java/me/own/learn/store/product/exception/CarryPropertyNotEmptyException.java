package me.own.learn.store.product.exception;

import me.own.commons.base.exception.BusinessException;

public class CarryPropertyNotEmptyException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "产品携带属性不可以为空！";
    }
}
