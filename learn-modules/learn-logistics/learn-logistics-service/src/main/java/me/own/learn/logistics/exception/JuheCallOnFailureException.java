package me.own.learn.logistics.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author yexudong
 * @date 2019/9/8 15:29
 */
public class JuheCallOnFailureException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "聚合数据api访问失败！";
    }
}
