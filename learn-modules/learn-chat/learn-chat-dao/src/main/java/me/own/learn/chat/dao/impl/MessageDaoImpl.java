package me.own.learn.chat.dao.impl;

import me.own.commons.base.dao.impl.BaseRedisListDaoImpl;
import me.own.learn.chat.dao.MessageDao;
import me.own.learn.chat.po.Message;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/1/7 11:30
 */
@Repository
public class MessageDaoImpl extends BaseRedisListDaoImpl<Long, Message> implements MessageDao {

    private static final String MESSAGE_PATTER = "learn:message:{{customerId}}";

    @Override
    protected String convertKey(Long key) {
        return convertKeyByCustomerId(key);
    }

    private String convertKeyByCustomerId(Long key) {
        return MESSAGE_PATTER.replace("{{customerId}}", String.valueOf(key));
    }

}
