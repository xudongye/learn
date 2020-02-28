package me.own.learn.chat.service.impl;

import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.dao.QueryOrder;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.chat.dao.ChatRobotDao;
import me.own.learn.chat.dao.ChatRoomDao;
import me.own.learn.chat.dto.ChatRoomDto;
import me.own.learn.chat.exception.ChatRoomNotFoundException;
import me.own.learn.chat.model.ChatRoomModel;
import me.own.learn.chat.po.ChatRobot;
import me.own.learn.chat.po.ChatRoom;
import me.own.learn.chat.service.ChatRoomService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/1/9 14:43
 */
@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatRoomServiceImpl.class);

    @Autowired
    private ChatRoomDao roomDao;

    @Autowired
    private ChatRobotDao robotDao;

    @Override
    @Transactional
    public ChatRoomModel create(ChatRoomDto dto) {
        ChatRoom chatRoom = Mapper.Default().map(dto, ChatRoom.class);
        chatRoom.onCreated();
        roomDao.create(chatRoom);
        LOGGER.info("customer {} 向 robot {} 发起了聊天", chatRoom.getCustomerId(), chatRoom.getRobotId());
        return Mapper.Default().map(chatRoom, ChatRoomModel.class);
    }

    @Override
    @Transactional
    public void delete(long roomId) {
        ChatRoom chatRoom = roomDao.get(roomId);
        if (chatRoom == null || chatRoom.getDeleted()) {
            throw new ChatRoomNotFoundException();
        }
        chatRoom.onDelete();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatRoomModel> getByRobotId(long robotId) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(ChatRoom.class);
        query.setDeletedFalseCondition();
        query.setSimpleCondition("robotId", robotId + "", QueryConstants.SimpleQueryMode.Equal);
        List<ChatRoom> chatRooms = roomDao.filter(query, null, new QueryOrder("createTime", QueryOrder.DESC));
        if (CollectionUtils.isNotEmpty(chatRooms)) {
            return Mapper.Default().mapArray(chatRooms, ChatRoomModel.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatRoomModel> getByCustomerId(long customerId) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(ChatRoom.class);
        query.setDeletedFalseCondition();
        query.setSimpleCondition("customerId", customerId + "", QueryConstants.SimpleQueryMode.Equal);
        List<ChatRoom> chatRooms = roomDao.filter(query, null, new QueryOrder("createTime", QueryOrder.DESC));
        if (CollectionUtils.isNotEmpty(chatRooms)) {
            return Mapper.Default().mapArray(chatRooms, ChatRoomModel.class);
        }
        return null;
    }
}
