package me.own.learn.sync.exception;

import me.own.learn.commons.base.exception.ObjectNotFoundException;

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
}
