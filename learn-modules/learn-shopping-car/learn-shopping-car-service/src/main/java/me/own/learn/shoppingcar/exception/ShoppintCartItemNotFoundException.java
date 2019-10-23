package me.own.learn.shoppingcar.exception;

import me.own.commons.base.exception.BusinessException;

public class ShoppintCartItemNotFoundException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "shopping cart item not found";
    }
}
