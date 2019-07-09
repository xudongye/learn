package me.own.learn.store.product.exception;

import me.own.commons.base.exception.ObjectNotFoundException;

/**
 * @author yexudong
 * @date 2019/7/4 11:57
 */
public class ProductNotFoundException extends ObjectNotFoundException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "未找到商品信息！";
    }
}
