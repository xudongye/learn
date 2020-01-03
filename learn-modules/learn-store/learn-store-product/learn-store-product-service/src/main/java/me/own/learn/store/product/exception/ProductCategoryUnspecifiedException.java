package me.own.learn.store.product.exception;

import me.own.commons.base.exception.BusinessException;

public class ProductCategoryUnspecifiedException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "商品未指定类目！";
    }
}
