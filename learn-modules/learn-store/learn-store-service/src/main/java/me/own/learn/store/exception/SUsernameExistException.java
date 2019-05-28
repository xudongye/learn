package me.own.learn.store.exception;

import me.own.learn.commons.base.exception.BusinessException;

/**
 * @author yexudong
 * @date 2019/5/15 14:46
 */
public class SUsernameExistException extends BusinessException {
    @Override
    public String getErrorCode() {
        return "202";
    }

    @Override
    public String getErrorMsg() {
        return "用户名已存在.";
    }
}
