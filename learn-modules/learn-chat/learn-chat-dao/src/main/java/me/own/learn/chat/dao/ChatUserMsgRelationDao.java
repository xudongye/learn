package me.own.learn.chat.dao;

import me.own.commons.base.dao.BaseDao;
import me.own.learn.chat.po.ChatUserMsgRelation;

/**
 * @author: yexudong
 * @Date: 2020/3/25 9:37
 */
public interface ChatUserMsgRelationDao extends BaseDao<ChatUserMsgRelation> {

    ChatUserMsgRelation getByMsgIdAndUserId(String msgId, long userId);

    void msgReadMark(String msgId, long userId);

    void msgDelete(String msgId, long userId);
}
