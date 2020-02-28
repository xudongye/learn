package me.own.learn.chat.exception;

import me.own.commons.base.exception.ObjectNotFoundException;

/**
 * @author: yexudong
 * @Date: 2020/1/9 15:12
 */
public class ChatRoomNotFoundException extends ObjectNotFoundException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "聊天室信息不存在！";
    }
}
