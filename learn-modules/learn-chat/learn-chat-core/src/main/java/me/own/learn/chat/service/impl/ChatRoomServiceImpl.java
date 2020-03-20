package me.own.learn.chat.service.impl;

import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.dao.QueryOrder;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.admin.service.AdminService;
import me.own.learn.admin.vo.AdminVo;
import me.own.learn.chat.dao.ChatRoomDao;
import me.own.learn.chat.dto.ChatRoomDto;
import me.own.learn.chat.exception.ChatRoomNotFoundException;
import me.own.learn.chat.po.ChatRoom;
import me.own.learn.chat.service.ChatRoomService;
import me.own.learn.chat.vo.ChatRoomVo;
import me.own.learn.customer.service.CustomerService;
import me.own.learn.customer.vo.CustomerVo;
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
    private CustomerService customerService;
    @Autowired
    private AdminService adminService;

    @Override
    @Transactional(readOnly = true)
    public ChatRoomVo getById(long id) {
        ChatRoom chatRoom = roomDao.get(id);
        if (chatRoom == null || chatRoom.getDeleted()) {
            return null;
        }
        return Mapper.Default().map(chatRoom, ChatRoomVo.class);
    }

    @Override
    @Transactional
    public ChatRoomVo create(ChatRoomDto dto) {

        QueryCriteriaUtil query = new QueryCriteriaUtil(ChatRoom.class);
        query.setDeletedFalseCondition();
        query.setSimpleCondition("customerId", dto.getCustomerId() + "", QueryConstants.SimpleQueryMode.Equal);
        query.setSimpleCondition("userId", dto.getUserId() + "", QueryConstants.SimpleQueryMode.Equal);
        List<ChatRoom> chatRooms = roomDao.filter(query, null, null);
        ChatRoom chatRoom = null;
        if (CollectionUtils.isNotEmpty(chatRooms)) {
            chatRoom = chatRooms.get(0);
            chatRoom.setDeleted(false);
//            chatRoom.setuDeleted(false);
//            chatRoom.setcDeleted(false);
            chatRoom.setModifyTime(new Date());
            return Mapper.Default().map(chatRoom, ChatRoomVo.class);
        }
        chatRoom = Mapper.Default().map(dto, ChatRoom.class);
        chatRoom.onCreated();
//        chatRoom.setcDeleted(false);
//        chatRoom.setuDeleted(false);
        roomDao.create(chatRoom);
//        LOGGER.info("customer {} 向 robot {} 发起了聊天", chatRoom.getCustomerId(), chatRoom.getUserId());
        return Mapper.Default().map(chatRoom, ChatRoomVo.class);
    }

    @Override
    @Transactional
    public void cDelete(long roomId) {
        ChatRoom chatRoom = roomDao.get(roomId);
        if (chatRoom == null || chatRoom.getDeleted()) {
            throw new ChatRoomNotFoundException();
        }
//        chatRoom.setcDeleted(true);
    }

    @Override
    @Transactional
    public void uDelete(long roomId) {
        ChatRoom chatRoom = roomDao.get(roomId);
        if (chatRoom == null || chatRoom.getDeleted()) {
            throw new ChatRoomNotFoundException();
        }
//        chatRoom.setuDeleted(true);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatRoomVo> getRooms(long userId, long customerId) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(ChatRoom.class);
        query.setDeletedFalseCondition();

        if (userId != 0) {
            query.setSimpleCondition("uDeleted", false + "", QueryConstants.SimpleQueryMode.Equal);
            query.setSimpleCondition("userId", userId + "", QueryConstants.SimpleQueryMode.Equal);
        }
        if (customerId != 0) {
            query.setSimpleCondition("cDeleted", false + "", QueryConstants.SimpleQueryMode.Equal);
            query.setSimpleCondition("customerId", customerId + "", QueryConstants.SimpleQueryMode.Equal);
        }
        List<ChatRoom> chatRooms = roomDao.filter(query, null, new QueryOrder("createTime", QueryOrder.DESC));
        if (CollectionUtils.isNotEmpty(chatRooms)) {
            List<ChatRoomVo> chatRoomVos = Mapper.Default().mapArray(chatRooms, ChatRoomVo.class);
            roomName(chatRoomVos);
            return chatRoomVos;
        }
        return null;
    }

    private void roomName(List<ChatRoomVo> roomVos) {
        for (ChatRoomVo chatRoomVo : roomVos) {
            CustomerVo customerVo = customerService.getById(chatRoomVo.getCustomerId());
            chatRoomVo.setCustomername(customerVo.getNickName());
            AdminVo adminVo = adminService.getById(chatRoomVo.getUserId());
            chatRoomVo.setUsername(adminVo.getName());
        }
    }
}
