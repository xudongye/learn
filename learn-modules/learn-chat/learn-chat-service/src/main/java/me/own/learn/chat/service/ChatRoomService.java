package me.own.learn.chat.service;

import me.own.learn.chat.dto.ChatRoomDto;
import me.own.learn.chat.model.ChatRoomModel;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/1/9 14:30
 */
public interface ChatRoomService {

    ChatRoomModel create(ChatRoomDto dto);

    void delete(long roomId);

    List<ChatRoomModel> getByRobotId(long robotId);

    List<ChatRoomModel> getByCustomerId(long customerId);
}
