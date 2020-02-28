package me.own.learn.chat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.learn.chat.dto.ChatRoomDto;
import me.own.learn.chat.model.ChatRoomModel;
import me.own.learn.chat.model.MessageModel;
import me.own.learn.chat.service.ChatRoomService;
import me.own.learn.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    private ChatRoomService roomService;

    @Autowired
    private MessageService messageService;

    @ApiOperation("发起聊天-创建聊天室")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity callOnRoom(HttpServletRequest request, ChatRoomDto roomDto) {
        Map<String, Object> responseBody = new HashMap<>();
        ChatRoomModel chatRoomModel = roomService.create(roomDto);
        responseBody.put("code", 200);
        responseBody.put("data", chatRoomModel);
        return new ResponseEntity(responseBody, HttpStatus.ACCEPTED);
    }

    @ApiOperation("通过用户 customer id 获取所在聊天室列表")
    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
    public ResponseEntity listChatRoomsByCustomerId(HttpServletRequest request, @PathVariable long customerId) {
        Map<String, Object> responseBody = new HashMap<>();
        List<ChatRoomModel> roomModels = roomService.getByCustomerId(customerId);
        responseBody.put("code", 200);
        responseBody.put("data", roomModels);
        return new ResponseEntity(responseBody, HttpStatus.ACCEPTED);
    }

    @ApiOperation("通过客服 robot id 获取聊天室列表")
    @RequestMapping(value = "/robot/{robotId}", method = RequestMethod.GET)
    public ResponseEntity listChatRoomsByRobotId(HttpServletRequest request, @PathVariable long robotId) {
        Map<String, Object> responseBody = new HashMap<>();
        List<ChatRoomModel> roomModels = roomService.getByRobotId(robotId);
        responseBody.put("code", 200);
        responseBody.put("data", roomModels);
        return new ResponseEntity(responseBody, HttpStatus.OK);
    }

    @ApiOperation("通过聊天室id获取聊天室所有聊天记录")
    @RequestMapping(value = "/msg/{roomId}", method = RequestMethod.GET)
    public ResponseEntity listMsgInChatRoom(HttpServletRequest request, @PathVariable long roomId) {
        Map<String, Object> responseBody = new HashMap<>();
        List<MessageModel> messageModels = messageService.getMsgByChatRoomId(roomId);
        responseBody.put("code", 200);
        responseBody.put("data", messageModels);
        return new ResponseEntity(responseBody, HttpStatus.OK);
    }


}
