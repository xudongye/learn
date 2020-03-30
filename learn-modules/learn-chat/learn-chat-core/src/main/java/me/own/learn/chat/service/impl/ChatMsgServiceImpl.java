package me.own.learn.chat.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.dao.QueryOrder;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.chat.dao.ChatMsgDao;
import me.own.learn.chat.dao.ChatMsgUserRelationDao;
import me.own.learn.chat.model.ChannelModel;
import me.own.learn.chat.model.ContentModel;
import me.own.learn.chat.model.LinkModel;
import me.own.learn.chat.po.ChatMsg;
import me.own.learn.chat.po.ChatMsgUserRelation;
import me.own.learn.chat.service.ChatMsgService;
import me.own.learn.chat.model.Channel;
import me.own.learn.chat.vo.ChatMessageVo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author: yexudong
 * @Date: 2020/1/9 17:38
 */
@Service
public class ChatMsgServiceImpl implements ChatMsgService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatMsgServiceImpl.class);

    @Autowired
    private ChatMsgDao chatMsgDao;

    @Autowired
    private ChatMsgUserRelationDao userMsgRelationDao;

    @Override
    @Transactional
    public void save(ContentModel messageModel) {
        ChatMsg chatMsg = new ChatMsg();
        chatMsg.setContent(messageModel.getContent());
        chatMsg.setSendTime(new Date());
        chatMsg.setType(messageModel.getType());
        chatMsg.setUserId(messageModel.getFrom());
        chatMsgDao.create(chatMsg);
        LOGGER.info("new msg {} ,content :{}", chatMsg.getMsgId(), chatMsg.getContent());

        for (Long to : messageModel.getTos()) {
            ChatMsgUserRelation relation = new ChatMsgUserRelation();
            relation.setDeleted(false);
            relation.setReadMark(false);
            relation.setMsgId(chatMsg.getMsgId());
            relation.setUserId(to);
            userMsgRelationDao.create(relation);
            LOGGER.info("send to user {}", to);
        }

    }

    @Override
    @Transactional
    public void readMark(String msgId, long userId) {
        userMsgRelationDao.msgReadMark(msgId, userId);
    }

    @Override
    @Transactional
    public void delete(String msgId, long userId) {
        userMsgRelationDao.msgDelete(msgId, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChannelModel> getChatListByUserId(long userId) {
        List<Channel> channels = chatMsgDao.getByUserId(userId);
        if (CollectionUtils.isNotEmpty(channels)) {
            return Mapper.Default().mapArray(channels, ChannelModel.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<ChatMessageVo> getMsgByChatRoomId(int pageNum, int pageSize, long roomId) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(ChatMsg.class);
        query.setDeletedFalseCondition();
        query.setSimpleCondition("roomId", roomId + "", QueryConstants.SimpleQueryMode.Equal);
        query.setSimpleCondition("markRead", false + "", QueryConstants.SimpleQueryMode.Equal);
        List<QueryOrder> queryOrders = new ArrayList<>();
        queryOrders.add(new QueryOrder("sendTime", QueryOrder.DESC));
        PageQueryResult<ChatMsg> result = chatMsgDao.pageQuery(pageNum, pageSize, query, queryOrders);

        return result.mapItems(ChatMessageVo.class);
    }

    @Override
    @Transactional
    public List<ChatMessageVo> listAllUnReadMsg(long roomId, long customerId, long userId) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(ChatMsg.class);
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
        List<ChatMsg> messages = chatMsgDao.filter(query, null, new QueryOrder("sendTime", QueryOrder.DESC));
        if (CollectionUtils.isNotEmpty(messages)) {
            return Mapper.Default().mapArray(messages, ChatMessageVo.class);
        }
        return null;
    }

    @Override
    @Transactional
    public Long count(long roomId, long customerId, long userId) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(ChatMsg.class);
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
        return chatMsgDao.getCount(query);
    }

    @Override
    @Transactional(readOnly = true)
    public LinkModel getByUserId(long userId) {
        LinkModel linkModel = new LinkModel();
        linkModel.setMsgCount(0);
        return null;
    }
}
