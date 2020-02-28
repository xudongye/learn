package me.own.learn.chat.service.impl;

import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.chat.dao.MessageDao;
import me.own.learn.chat.model.MessageModel;
import me.own.learn.chat.po.Message;
import me.own.learn.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/1/9 17:38
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public void save(MessageModel messageModel) {
        Message message = Mapper.Default().map(messageModel, Message.class);
        message.setTimestamp(Calendar.getInstance().getTimeInMillis());
        messageDao.leftPush(messageModel.getChatRoomId(), message);
    }

    @Override
    public List<MessageModel> getMsgByChatRoomId(long roomId) {
        List<Message> messages = messageDao.getAll(roomId);

        return null;
    }
}
