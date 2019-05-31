package me.own.learn.role.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author yexudong
 * @date 2019/5/29 16:09
 */
public class PermissionDoNotGiveException extends BusinessException {
    @Override
    public String getErrorCode() {
        return "";
    }

    @Override
    public String getErrorMsg() {
        return "不可授权该权限(或已拥有)！";
    }
}
