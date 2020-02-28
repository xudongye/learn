package me.own.learn.chat.service.impl;

import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.chat.dao.ChatRobotDao;
import me.own.learn.chat.exception.ChatRobotIsNotSetException;
import me.own.learn.chat.po.ChatRobot;
import me.own.learn.chat.service.ChatRobotService;
import me.own.learn.chat.dto.ChatRobotDto;
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
public class ChatRobotServiceImpl implements ChatRobotService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatRobotServiceImpl.class);

    @Autowired
    private ChatRobotDao robotDao;

    @Override
    @Transactional(readOnly = true)
    public ChatRobotDto getRandomRobot() {
        QueryCriteriaUtil query = new QueryCriteriaUtil(ChatRobot.class);
        query.setDeletedFalseCondition();
        query.setSimpleCondition("online", true + "", QueryConstants.SimpleQueryMode.Equal);
        List<ChatRobot> chatRobots = robotDao.filter(query, null, null);
        if (CollectionUtils.isEmpty(chatRobots)) {
            throw new ChatRobotIsNotSetException();
        }
        ChatRobot chatRobot;
        int size = chatRobots.size();
        if (size == 1) {
            chatRobot = chatRobots.get(0);
        } else {
            chatRobot = chatRobots.get(new Random().nextInt(size));
        }
        LOGGER.info("随机抽取客服机器账号：{},名称：{}", chatRobot.getId(), chatRobot.getName());
        return Mapper.Default().map(chatRobot, ChatRobotDto.class);
    }
}
