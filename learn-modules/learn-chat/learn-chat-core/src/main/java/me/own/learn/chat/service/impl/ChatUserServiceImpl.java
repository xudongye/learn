package me.own.learn.chat.service.impl;

import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.chat.dao.ChatUserDao;
import me.own.learn.chat.exception.ChatUserNotFountException;
import me.own.learn.chat.po.ChatUser;
import me.own.learn.chat.service.ChatUserService;
import me.own.learn.chat.vo.ChatUserVo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

/**
 * @author: yexudong
 * @Date: 2020/1/8 14:57
 */
@Service
public class ChatUserServiceImpl implements ChatUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatUserServiceImpl.class);

    @Autowired
    private ChatUserDao robotDao;

    @Override
    @Transactional(readOnly = true)
    public ChatUserVo getById(long id) {
        ChatUser user = robotDao.get(id);
        if (user == null) {
            throw new ChatUserNotFountException();
        }
        return Mapper.Default().map(user, ChatUserVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public ChatUserVo getRandomRobot() {
        QueryCriteriaUtil query = new QueryCriteriaUtil(ChatUser.class);
        query.setDeletedFalseCondition();
        query.setSimpleCondition("online", true + "", QueryConstants.SimpleQueryMode.Equal);
        List<ChatUser> chatUsers = robotDao.filter(query, null, null);
        if (CollectionUtils.isEmpty(chatUsers)) {
            throw new ChatUserNotFountException();
        }
        ChatUser chatUser;
        int size = chatUsers.size();
        if (size == 1) {
            chatUser = chatUsers.get(0);
        } else {
            chatUser = chatUsers.get(new Random().nextInt(size));
        }
        LOGGER.info("随机抽取客服机器账号：{},名称：{}", chatUser.getId(), chatUser.getName());
        return Mapper.Default().map(chatUser, ChatUserVo.class);
    }
}
