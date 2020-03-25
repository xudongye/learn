package me.own.learn.chat.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.chat.dao.ChatMsgDao;
import me.own.learn.chat.po.ChatMsg;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/1/7 11:30
 */
@Repository
public class ChatMsgDaoImpl extends BaseDaoImpl<ChatMsg> implements ChatMsgDao {

    @Override
    protected Class<ChatMsg> getEntityClass() {
        return ChatMsg.class;
    }

}
