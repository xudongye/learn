package me.own.learn.sync.exception;

import me.own.learn.commons.base.exception.BusinessException;

/**
 * @author yexudong
 * @date 2019/4/22 13:11
 */
public class ResultSizeEmptyException extends BusinessException {
    @Override
    public String getErrorCode() {
        return "20108";
    }

    @Override
    public String getErrorMsg() {
        return "result be empty.";
    }
}
