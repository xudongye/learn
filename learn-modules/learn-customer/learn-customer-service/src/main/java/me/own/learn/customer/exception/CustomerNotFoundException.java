package me.own.learn.customer.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author yexudong
 * @date 2019/5/31 13:47
 */
public class CustomerNotFoundException extends BusinessException {
    @Override
    public String getErrorCode() {
        return "";
    }

    @Override
    public String getErrorMsg() {
        return "未找到用户！";
    }
}
