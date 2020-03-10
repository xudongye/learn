package me.own.learn.mall.member.service;

import me.own.learn.mall.member.vo.MallMemberLevelVo;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/9 17:57
 */
public interface MallMemberLevelService {

    List<MallMemberLevelVo> listByDefaultStatus(Integer defaultStatus);
}
