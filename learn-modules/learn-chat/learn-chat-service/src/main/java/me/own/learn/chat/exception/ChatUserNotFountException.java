package me.own.learn.chat.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author: yexudong
 * @Date: 2020/1/8 15:06
 */
public class ChatUserNotFountException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "聊天账号不存在！！";
    }
}
