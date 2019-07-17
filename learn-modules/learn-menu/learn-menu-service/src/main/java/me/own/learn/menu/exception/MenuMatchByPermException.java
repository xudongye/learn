package me.own.learn.menu.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author yexudong
 * @date 2019/7/13 22:12
 */
public class MenuMatchByPermException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "权限已经有匹配菜单！";
    }
}
