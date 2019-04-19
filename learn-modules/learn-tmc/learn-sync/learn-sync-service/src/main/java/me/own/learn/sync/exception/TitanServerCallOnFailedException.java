package me.own.learn.sync.exception;

import me.own.learn.commons.base.exception.BusinessException;
import org.springframework.http.HttpStatus;

/**
 * @author yexudong
 * @date 2019/4/19 10:55
 */
public class TitanServerCallOnFailedException extends BusinessException {
    @Override
    public String getErrorCode() {
        return "20103";
    }

    @Override
    public String getErrorMsg() {
        return "titan server call on failed";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
