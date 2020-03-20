package me.own.learn.chat.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.dao.QueryOrder;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.chat.dao.ChatMessageDao;
import me.own.learn.chat.model.ContentModel;
import me.own.learn.chat.po.ChatMessage;
import me.own.learn.chat.service.ChatMessageService;
import me.own.learn.chat.vo.ChatMessageVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author: yexudong
 * @Date: 2020/1/9 17:38
 */
@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    private ChatMessageDao chatMessageDao;

    @Override
    @Transactional
    public void save(ContentModel messageModel) {
        ChatMessage chatMessage = Mapper.Default().map(messageModel, ChatMessage.class);
        chatMessage.setDeleted(false);
        chatMessage.setMarkRead(false);
        chatMessage.setSendTime(new Date());
        chatMessageDao.create(chatMessage);
    }

    @Override
    @Transactional
    public void cBatchDelete(List<Long> ids) {
        chatMessageDao.cBatchDeleted(ids);
    }

    @Override
    @Transactional
    public void uBatchDelete(List<Long> ids) {
        chatMessageDao.uBatchDeleted(ids);
    }

    @Override
    @Transactional
    public void readMarked(List<Long> ids) {
        chatMessageDao.readMarked(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<ChatMessageVo> getMsgByChatRoomId(int pageNum, int pageSize, long roomId) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(ChatMessage.class);
        query.setDeletedFalseCondition();
        query.setSimpleCondition("roomId", roomId + "", QueryConstants.SimpleQueryMode.Equal);
        query.setSimpleCondition("markRead", false + "", QueryConstants.SimpleQueryMode.Equal);
        List<QueryOrder> queryOrders = new ArrayList<>();
        queryOrders.add(new QueryOrder("sendTime", QueryOrder.DESC));
        PageQueryResult<ChatMessage> result = chatMessageDao.pageQuery(pageNum, pageSize, query, queryOrders);

        return result.mapItems(ChatMessageVo.class);
    }

    @Override
    @Transactional
    public List<ChatMessageVo> listAllUnReadMsg(long roomId, long customerId, long userId) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(ChatMessage.class);
        query.setDeletedFalseCondition();
        query.setSimpleCondition("markRead", false + "", QueryConstants.SimpleQueryMode.Equal);
        if (roomId != 0) {
            query.setSimpleCondition("roomId", roomId + "", QueryConstants.SimpleQueryMode.Equal);
        }
        if (customerId != 0) {
            query.setSimpleCondition("customerId", customerId + "", QueryConstants.SimpleQueryMode.Equal);
        }
        if (userId != 0) {
            query.setSimpleCondition("userId", userId + "", QueryConstants.SimpleQueryMode.Equal);
        }
        List<ChatMessage> messages = chatMessageDao.filter(query, null, new QueryOrder("sendTime", QueryOrder.DESC));
        if (CollectionUtils.isNotEmpty(messages)) {
            return Mapper.Default().mapArray(messages, ChatMessageVo.class);
        }
        return null;
    }

    @Override
    @Transactional
    public Long count(long roomId, long customerId, long userId) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(ChatMessage.class);
        query.setDeletedFalseCondition();
        query.setSimpleCondition("markRead", false + "", QueryConstants.SimpleQueryMode.Equal);
        if (roomId != 0) {
            query.setSimpleCondition("roomId", roomId + "", QueryConstants.SimpleQueryMode.Equal);
        }
        if (customerId != 0) {
            query.setSimpleCondition("customerId", customerId + "", QueryConstants.SimpleQueryMode.Equal);
        }
        if (userId != 0) {
            query.setSimpleCondition("userId", userId + "", QueryConstants.SimpleQueryMode.Equal);
        }
        return chatMessageDao.getCount(query);
    }
}
