package me.own.learn.chat.service;

import me.own.learn.chat.dto.ChatRoomDto;
import me.own.learn.chat.vo.ChatRoomVo;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/1/9 14:30
 */
public interface ChatRoomService {
    ChatRoomVo getById(long id);

    ChatRoomVo create(ChatRoomDto dto);

    void cDelete(long roomId);

    void uDelete(long roomId);

    List<ChatRoomVo> getRooms(long userId,long customerId);

}
