package me.own.learn.mall.product.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author: yexudong
 * @Date: 2020/3/3 16:18
 */
public class ProductBrandNotFoundException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "商品品牌不存在！";
    }
}
