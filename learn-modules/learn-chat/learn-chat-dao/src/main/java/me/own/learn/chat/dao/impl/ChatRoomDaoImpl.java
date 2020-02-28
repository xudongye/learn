package me.own.learn.chat.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.chat.dao.ChatRoomDao;
import me.own.learn.chat.po.ChatRoom;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/1/9 14:30
 */
@Repository
public class ChatRoomDaoImpl extends BaseDaoImpl<ChatRoom> implements ChatRoomDao {
    @Override
    protected Class<ChatRoom> getEntityClass() {
        return ChatRoom.class;
    }
}
