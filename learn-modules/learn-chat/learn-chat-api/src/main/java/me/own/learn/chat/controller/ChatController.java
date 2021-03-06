package me.own.learn.chat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.chat.dto.ChatRoomDto;
import me.own.learn.chat.model.ChannelModel;
import me.own.learn.chat.service.ChatRoomService;
import me.own.learn.chat.service.ChatMsgService;
import me.own.learn.chat.vo.ChatMessageVo;
import me.own.learn.chat.vo.ChatRoomVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yexudong
 * @Date: 2020/1/9 15:29
 */
@RestController
@RequestMapping(value = "/api/v1/chats")
@Api(value = "客服聊天模块", description = "聊天功能模块")
public class ChatController {

    @Autowired
    private ChatMsgService chatMsgService;

    @ApiOperation("会员批量删除消息")
    @RequestMapping(value = "customer/msg", method = RequestMethod.DELETE)
    public ResponseEntity cBatchDeleted(HttpServletRequest request,
                                        @RequestParam(value = "ids") List<Long> ids) {
//        chatMsgService.cBatchDelete(ids);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("管理员批量删除消息")
    @RequestMapping(value = "user/msg", method = RequestMethod.DELETE)
    public ResponseEntity uBatchDeleted(HttpServletRequest request,
                                        @RequestParam(value = "ids") List<Long> ids) {
//        chatMsgService.uBatchDelete(ids);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("管理员批量删除消息")
    @RequestMapping(value = "user/msg", method = RequestMethod.PUT)
    public ResponseEntity readMarked(HttpServletRequest request,
                                     @RequestParam(value = "ids") List<Long> ids) {
//        chatMsgService.readMarked(ids);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @ApiOperation("获取聊天室缓存消息")
    @RequestMapping(value = "/{roomId}", method = RequestMethod.GET)
    public ResponseEntity pageMessage(HttpServletRequest request,
                                      @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                      @PathVariable Long roomId) {
        Map<String, Object> response = new HashMap<>();
        PageQueryResult<ChatMessageVo> result = chatMsgService.getMsgByChatRoomId(pageNum, pageSize, roomId);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("会员删除聊天室")
    @RequestMapping(value = "/customer/{roomId}", method = RequestMethod.DELETE)
    public ResponseEntity cDeleted(HttpServletRequest request,
                                   @PathVariable Long roomId) {
//        roomService.cDelete(roomId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("管理员删除聊天室")
    @RequestMapping(value = "/user/{roomId}", method = RequestMethod.DELETE)
    public ResponseEntity uDeleted(HttpServletRequest request,
                                   @PathVariable Long roomId) {
//        roomService.uDelete(roomId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("通过用户 customer id 获取所在聊天室列表")
    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
    public ResponseEntity listChatRoomsByCustomerId(HttpServletRequest request, @PathVariable long customerId) {
        Map<String, Object> responseBody = new HashMap<>();
//        List<ChatRoomVo> roomModels = roomService.getRooms(0, customerId);
        responseBody.put("code", 200);
//        responseBody.put("data", roomModels);
        return new ResponseEntity(responseBody, HttpStatus.ACCEPTED);
    }

    @ApiOperation("通过客服 user id 获取聊天室列表")
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity listChatRoomsByRobotId(HttpServletRequest request, @PathVariable long userId) {
        Map<String, Object> responseBody = new HashMap<>();
        List<ChannelModel> channelModels = chatMsgService.getChatListByUserId(userId);
        responseBody.put("code", 200);
        responseBody.put("data", channelModels);
        return new ResponseEntity(responseBody, HttpStatus.OK);
    }

}
