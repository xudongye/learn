package me.own.learn.chat.service;

import me.own.learn.chat.dto.ChatUserDto;
import me.own.learn.chat.vo.ChatUserVo;

/**
 * @author: yexudong
 * @Date: 2020/1/8 14:52
 */
public interface ChatUserService {

    ChatUserVo getById(long id);

    ChatUserVo getRandomRobot();
}
