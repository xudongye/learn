package me.own.learn.authorization.exception;

import me.own.learn.commons.base.exception.BusinessException;

/**
 * @author yexudong
 * @date 2019/5/30 10:33
 */
public class PasswordWrongException extends BusinessException {
    @Override
    public String getErrorCode() {
        return "";
    }

    @Override
    public String getErrorMsg() {
        return "密码错误！";
    }
}
