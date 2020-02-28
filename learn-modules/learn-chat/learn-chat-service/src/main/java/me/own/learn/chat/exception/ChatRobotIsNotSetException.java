package me.own.learn.chat.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author: yexudong
 * @Date: 2020/1/8 15:06
 */
public class ChatRobotIsNotSetException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "系统未设置客服人员账号！";
    }
}
