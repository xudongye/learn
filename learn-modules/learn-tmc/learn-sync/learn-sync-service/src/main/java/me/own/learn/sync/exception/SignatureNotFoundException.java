package me.own.learn.sync.exception;

import me.own.commons.base.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;

/**
 * @author:simonye
 * @date 21:01 2019/4/16
 **/
public class SignatureNotFoundException extends ObjectNotFoundException {
    @Override
    public String getErrorCode() {
        return "20102";
    }

    @Override
    public String getErrorMsg() {
        return "signature not found.";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
