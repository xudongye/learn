package me.own.learn.sync.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author yexudong
 * @date 2019/4/22 12:15
 */
public class ResponseFailedException extends BusinessException {

    private String errorMsg;

    public ResponseFailedException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getErrorCode() {
        return "20106";
    }

    @Override
    public String getErrorMsg() {
        return "FAILED RESP:" + errorMsg;
    }
}
