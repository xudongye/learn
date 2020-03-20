package me.own.learn.chat.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.chat.dao.ChatMessageDao;
import me.own.learn.chat.po.ChatMessage;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author: yexudong
 * @Date: 2020/1/7 11:30
 */
@Repository
public class ChatMessageDaoImpl extends BaseDaoImpl<ChatMessage> implements ChatMessageDao {

    @Override
    protected Class<ChatMessage> getEntityClass() {
        return ChatMessage.class;
    }

    @Override
    public void cBatchDeleted(List<Long> ids) {
        Query query = getCurrentSession().createQuery("update ChatMessage set cDeleted = true where id in :ids");
        query.setParameterList("ids", ids);
        query.executeUpdate();
    }

    @Override
    public void uBatchDeleted(List<Long> ids) {
        Query query = getCurrentSession().createQuery("update ChatMessage set uDeleted = true where id in :ids");
        query.setParameterList("ids", ids);
        query.executeUpdate();
    }

    @Override
    public void readMarked(List<Long> ids) {
        Query query = getCurrentSession().createQuery("update ChatMessage set readMarked = true where id in :ids");
        query.setParameterList("ids", ids);
        query.executeUpdate();
    }
}
