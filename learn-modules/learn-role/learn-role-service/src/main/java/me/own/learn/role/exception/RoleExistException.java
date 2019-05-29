package me.own.learn.role.exception;

import me.own.learn.commons.base.exception.BusinessException;

/**
 * @author yexudong
 * @date 2019/5/29 16:38
 */
public class RoleExistException extends BusinessException {
    @Override
    public String getErrorCode() {
        return "";
    }

    @Override
    public String getErrorMsg() {
        return "角色已存在！";
    }
}
