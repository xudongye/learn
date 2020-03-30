package me.own.learn.chat.dao;

import me.own.commons.base.dao.BaseDao;
import me.own.learn.chat.model.Channel;
import me.own.learn.chat.po.ChatMsg;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/1/7 11:30
 */
public interface ChatMsgDao extends BaseDao<ChatMsg> {

    List<Channel> getByUserId(long userId);
}
