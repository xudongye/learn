package me.own.learn.store.product.exception;

import me.own.commons.base.exception.BusinessException;

public class ProductCategoryCanNotNullException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "创建产品必须指定产品类目！";
    }
}
