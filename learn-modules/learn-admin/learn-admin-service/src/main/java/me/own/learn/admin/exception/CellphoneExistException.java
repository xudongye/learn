package me.own.learn.admin.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author yexudong
 * @date 2019/5/28 14:43
 */
public class CellphoneExistException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "手机号已绑定！";
    }
}
