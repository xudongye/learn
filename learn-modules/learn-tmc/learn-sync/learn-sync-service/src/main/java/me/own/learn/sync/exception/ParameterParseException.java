package me.own.learn.sync.exception;

import me.own.learn.commons.base.exception.BusinessException;

/**
 * @author yexudong
 * @date 2019/4/22 12:51
 */
public class ParameterParseException extends BusinessException {

    @Override
    public String getErrorCode() {
        return "20107";
    }

    @Override
    public String getErrorMsg() {
        return "parameter parse error.";
    }
}
