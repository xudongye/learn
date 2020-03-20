package me.own.learn.chat.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.chat.model.ContentModel;
import me.own.learn.chat.vo.ChatMessageVo;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/1/7 11:38
 */
public interface ChatMessageService {

    //缓存存储
    void save(ContentModel messageModel);

    void cBatchDelete(List<Long> ids);

    void uBatchDelete(List<Long> ids);

    void readMarked(List<Long> ids);

    PageQueryResult<ChatMessageVo> getMsgByChatRoomId(int pageNum, int pageSize, long roomId);

    List<ChatMessageVo> listAllUnReadMsg(long roomId, long customerId, long userId);

    Long count(long roomId, long customerId, long userId);
}
