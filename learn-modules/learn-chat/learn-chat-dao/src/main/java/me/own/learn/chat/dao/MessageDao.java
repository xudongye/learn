package me.own.learn.chat.dao;

import me.own.commons.base.dao.BaseRedisListDao;
import me.own.learn.chat.po.Message;

/**
 * @author: yexudong
 * @Date: 2020/1/7 11:30
 */
public interface MessageDao extends BaseRedisListDao<Long, Message> {
}
