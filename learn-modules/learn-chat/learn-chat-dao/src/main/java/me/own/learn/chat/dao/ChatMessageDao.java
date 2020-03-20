package me.own.learn.chat.dao;

import me.own.commons.base.dao.BaseDao;
import me.own.learn.chat.po.ChatMessage;

import java.util.List;
import java.util.Set;

/**
 * @author: yexudong
 * @Date: 2020/1/7 11:30
 */
public interface ChatMessageDao extends BaseDao<ChatMessage> {

    void cBatchDeleted(List<Long> ids);

    void uBatchDeleted(List<Long> ids);

    void readMarked(List<Long> ids);
}
