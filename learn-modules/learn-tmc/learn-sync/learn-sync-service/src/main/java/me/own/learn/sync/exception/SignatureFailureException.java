package me.own.learn.sync.exception;

import me.own.learn.commons.base.exception.BusinessException;

/**
 * @author:simonye
 * @date 20:56 2019/4/16
 **/

public class SignatureFailureException extends BusinessException {
    @Override
    public String getErrorCode() {
        return "20101";
    }

    @Override
    public String getErrorMsg() {
        return "signature failure.";
    }
}
