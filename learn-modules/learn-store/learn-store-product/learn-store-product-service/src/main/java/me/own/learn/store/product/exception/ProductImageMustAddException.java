package me.own.learn.store.product.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author: yexudong
 * @Date: 2020/2/28 11:45
 */
public class ProductImageMustAddException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "商品详情图片必须添加3-4张！";
    }
}
