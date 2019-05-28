package me.own.learn.admin.exception;

import me.own.learn.commons.base.exception.BusinessException;

/**
 * @author yexudong
 * @date 2019/5/28 14:40
 */
public class AdminNameExistException extends BusinessException {
    @Override
    public String getErrorCode() {
        return "";
    }

    @Override
    public String getErrorMsg() {
        return "用户名已存在！";
    }
}
