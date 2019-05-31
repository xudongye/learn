package me.own.learn.role.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author yexudong
 * @date 2019/5/29 16:00
 */
public class RoleNotFoundException extends BusinessException {
    @Override
    public String getErrorCode() {
        return "";
    }

    @Override
    public String getErrorMsg() {
        return "角色不存在！";
    }
}
