package me.own.learn.store.product.exception;

import me.own.commons.base.exception.BusinessException;

public class ProductCanNotBindParentCategoryException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "产品不可绑定父级类目！";
    }
}
