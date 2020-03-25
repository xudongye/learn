package me.own.learn.chat.service.impl.config;

import me.own.learn.chat.service.ChatMsgService;
import me.own.learn.chat.service.ChatRoomService;
import me.own.learn.chat.service.ChatUserService;
import me.own.learn.chat.service.impl.websocket.WebsocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: yexudong
 * @Date: 2020/3/19 13:51
 */
@Configuration
public class WebSocketConfig {

    /**
     * ServerEndpointExporter 用于扫描和注册所有携带 ServerEndPoint 注解的实例，
     * 若部署到外部容器 则无需提供此类。
     *
     * @return
     */
    @Bean
    public WebsocketServer serverEndpointExporter() {
        return new WebsocketServer();
    }


    /**
     * 因 Spring WebSocket 对每个客户端连接都会创建一个 WebSocketServer（@ServerEndpoint 注解对应的） 对象，Bean 注入操作会被直接略过，因而手动注入一个全局变量
     *
     * @param messageService
     */
    @Autowired
    public void setMessageService(ChatMsgService messageService) {
        WebsocketServer.chatMsgService = messageService;
    }

    @Autowired
    public void setRoomService(ChatRoomService roomService) {
        WebsocketServer.chatRoomService = roomService;
    }

    @Autowired
    public void setUserService(ChatUserService userService) {
        WebsocketServer.chatUserService = userService;
    }
}
