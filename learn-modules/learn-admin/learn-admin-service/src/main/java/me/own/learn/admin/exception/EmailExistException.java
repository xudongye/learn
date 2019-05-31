package me.own.learn.admin.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author yexudong
 * @date 2019/5/28 14:43
 */
public class EmailExistException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "邮箱已绑定用户!";
    }
}
