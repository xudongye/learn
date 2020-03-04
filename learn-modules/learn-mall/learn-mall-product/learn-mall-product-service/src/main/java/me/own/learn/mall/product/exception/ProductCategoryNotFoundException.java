package me.own.learn.mall.product.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author: yexudong
 * @Date: 2020/3/4 11:22
 */
public class ProductCategoryNotFoundException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "商品分类不存在！";
    }
}
