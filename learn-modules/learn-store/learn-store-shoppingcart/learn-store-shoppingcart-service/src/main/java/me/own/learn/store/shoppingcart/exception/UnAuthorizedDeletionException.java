package me.own.learn.store.shoppingcart.exception;

import me.own.commons.base.exception.BusinessException;

public class UnAuthorizedDeletionException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "shopping cart item not belong to you";
    }
}
