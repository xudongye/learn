package me.own.learn.chat.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.chat.dao.ChatUserMsgRelationDao;
import me.own.learn.chat.po.ChatUserMsgRelation;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/25 9:37
 */
@Repository
public class ChatUserMsgRelationDaoImpl extends BaseDaoImpl<ChatUserMsgRelation> implements ChatUserMsgRelationDao {
    @Override
    protected Class<ChatUserMsgRelation> getEntityClass() {
        return ChatUserMsgRelation.class;
    }

    @Override
    public ChatUserMsgRelation getByMsgIdAndUserId(String msgId, long userId) {
        Criteria criteria = getCurrentSession().createCriteria(ChatUserMsgRelation.class);
        criteria.add(Restrictions.eq("msgId", msgId));
        criteria.add(Restrictions.eq("userId", userId));
        return (ChatUserMsgRelation) criteria.uniqueResult();
    }

    @Override
    public void msgReadMark(String msgId, long userId) {
        Query query = getCurrentSession().createQuery("UPDATE ChatUserMsgRelation SET read = TRUE WHERE msgId = :msgId AND userId = :userId");
        query.setParameter("msgId", msgId);
        query.setParameter("userId", userId);
        query.executeUpdate();
    }

    @Override
    public void msgDelete(String msgId, long userId) {
        Query query = getCurrentSession().createQuery("UPDATE ChatUserMsgRelation SET deleted = TRUE WHERE msgId = :msgId AND userId = :userId");
        query.setParameter("msgId", msgId);
        query.setParameter("userId", userId);
        query.executeUpdate();
    }
}
