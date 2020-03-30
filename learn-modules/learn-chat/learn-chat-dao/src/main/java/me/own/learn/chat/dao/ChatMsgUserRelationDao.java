package me.own.learn.chat.dao;

import me.own.commons.base.dao.BaseDao;
import me.own.learn.chat.po.ChatMsgUserRelation;

/**
 * @author: yexudong
 * @Date: 2020/3/25 9:37
 */
public interface ChatMsgUserRelationDao extends BaseDao<ChatMsgUserRelation> {

    ChatMsgUserRelation getByMsgIdAndUserId(String msgId, long userId);

    void msgReadMark(String msgId, long userId);

    void msgDelete(String msgId, long userId);
}
