package me.own.learn.chat.service;

import me.own.learn.chat.model.MessageModel;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/1/7 11:38
 */
public interface MessageService {

    //缓存消息
    void save(MessageModel messageModel);

    List<MessageModel> getMsgByChatRoomId(long roomId);
}
