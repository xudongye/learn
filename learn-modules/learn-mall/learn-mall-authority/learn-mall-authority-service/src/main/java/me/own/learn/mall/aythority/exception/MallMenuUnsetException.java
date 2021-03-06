package me.own.learn.mall.aythority.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author: yexudong
 * @Date: 2020/3/2 20:48
 */
public class MallMenuUnsetException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "菜单列表为空或未设置！";
    }
}
