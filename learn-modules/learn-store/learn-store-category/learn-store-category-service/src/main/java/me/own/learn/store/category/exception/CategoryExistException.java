package me.own.learn.store.category.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author yexudong
 * @date 2019/6/12 15:33
 */
public class CategoryExistException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "类目已经存在！";
    }
}
