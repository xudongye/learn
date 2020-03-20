package me.own.learn.chat.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.chat.dao.ChatUserDao;
import me.own.learn.chat.po.ChatUser;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/1/8 14:51
 */
@Repository
public class ChatUserDaoImpl extends BaseDaoImpl<ChatUser> implements ChatUserDao {
    @Override
    protected Class<ChatUser> getEntityClass() {
        return ChatUser.class;
    }
}
