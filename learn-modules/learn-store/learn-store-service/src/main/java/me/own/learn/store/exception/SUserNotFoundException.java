package me.own.learn.store.exception;

import me.own.learn.commons.base.exception.BusinessException;

/**
 * @author yexudong
 * @date 2019/5/15 14:34
 */
public class SUserNotFoundException extends BusinessException {
    @Override
    public String getErrorCode() {
        return "201";
    }

    @Override
    public String getErrorMsg() {
        return "未找到用户";
    }
}
