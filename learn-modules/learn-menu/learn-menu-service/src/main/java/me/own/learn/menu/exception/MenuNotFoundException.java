package me.own.learn.menu.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author:simonye
 * @date 22:43 2019/6/1
 **/
public class MenuNotFoundException extends BusinessException {
    @Override
    public String getErrorCode() {
        return "";
    }

    @Override
    public String getErrorMsg() {
        return "未找到菜单！";
    }
}
