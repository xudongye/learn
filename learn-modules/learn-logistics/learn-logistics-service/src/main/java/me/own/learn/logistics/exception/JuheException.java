package me.own.learn.logistics.exception;

import me.own.commons.base.exception.BusinessException;

public class JuheException extends BusinessException {

    private String juhMessage;

    public JuheException(String juhMessage) {
        this.juhMessage = juhMessage;
    }

    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return juhMessage;
    }

    public String getJuhMessage() {
        return juhMessage;
    }

    public void setJuhMessage(String juhMessage) {
        this.juhMessage = juhMessage;
    }
}
