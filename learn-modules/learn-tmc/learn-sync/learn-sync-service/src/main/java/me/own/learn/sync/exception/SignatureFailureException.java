package me.own.learn.sync.exception;

import me.own.commons.base.exception.BusinessException;
import org.springframework.http.HttpStatus;

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

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
