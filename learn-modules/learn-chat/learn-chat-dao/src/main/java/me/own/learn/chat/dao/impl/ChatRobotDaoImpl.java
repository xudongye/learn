package me.own.learn.chat.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.chat.dao.ChatRobotDao;
import me.own.learn.chat.po.ChatRobot;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/1/8 14:51
 */
@Repository
public class ChatRobotDaoImpl extends BaseDaoImpl<ChatRobot> implements ChatRobotDao {
    @Override
    protected Class<ChatRobot> getEntityClass() {
        return ChatRobot.class;
    }
}
