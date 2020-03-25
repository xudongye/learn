package me.own.learn.chat.service.impl.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.own.learn.chat.model.ContentModel;
import me.own.learn.chat.model.MessageModel;
import me.own.learn.chat.service.ChatMsgService;
import me.own.learn.chat.service.ChatRoomService;
import me.own.learn.chat.service.ChatUserService;
import me.own.learn.chat.vo.ChatUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author: yexudong
 * @Date: 2020/1/19 9:49
 */
@ServerEndpoint("/echo/{userId}")
@Component
public class WebsocketServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketServer.class);

    public static ChatRoomService chatRoomService;

    public static ChatMsgService chatMsgService;

    public static ChatUserService chatUserService;

    private ObjectMapper mapper = new ObjectMapper();

    private Session session;

    private String username;

    private Long userId;

    private static CopyOnWriteArraySet<WebsocketServer> webSockets = new CopyOnWriteArraySet<>();

    private static Map<String, Long> map = new HashMap<>();

    /**
     * 用户接入
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam(value = "userId") Long userId, Session session) throws JsonProcessingException {
        this.session = session;
        this.userId = userId;
        webSockets.add(this);
        ChatUserVo chatUserVo = chatUserService.getById(userId);
        username = chatUserVo.getName();
        map.put(session.getId(), userId);
        String content = username + "进入聊天室！";
        System.out.println("有新的连接，总数：" + webSockets.size() + "  sessionId：" + session.getId() + "  用户" + username);
        MessageModel messageModel = new MessageModel(content, map);
        sendMsg(messageModel.toJson());
    }

    /**
     * 接收到来自用户的消息
     *
     * @param message
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String message) throws IOException {

        ContentModel contentModel = mapper.readValue(message, ContentModel.class);

        //tos size > 1 群聊
        if (contentModel.getTo() == null) {
            MessageModel messageModel = new MessageModel();
            messageModel.setContent(contentModel.getContent());
            messageModel.setUserIds(map);
            sendMsg(messageModel.toJson());
        } else {//单聊
            MessageModel messageModel = new MessageModel();
            messageModel.setContent(contentModel.getContent());
            messageModel.setUserIds(map);
            for (WebsocketServer webSocket : webSockets) {
                if (webSocket.userId == contentModel.getTo().longValue() && webSocket.session.getId() != this.session.getId()) {
                    try {
                        webSocket.session.getBasicRemote().sendText(messageModel.toJson());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    public void sendMsg(String content) {
        for (WebsocketServer webSocket : webSockets) {
            try {
                webSocket.session.getBasicRemote().sendText(content);
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }

    }

    /**
     * 用户断开
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) throws JsonProcessingException {
        webSockets.remove(this);
        map.remove(session.getId());
        System.out.println("有新的断开，总数：" + webSockets.size() + "  sessionId：" + session.getId());
        String content = username + "离开了聊天室！";
        MessageModel message = new MessageModel(content, map);
        sendMsg(message.toJson());
    }

    /**
     * 用户连接异常
     *
     * @param t
     */
    @OnError
    public void onError(Throwable t) {
        LOGGER.error("连接异常！reason:{}", t.getCause().getMessage());
    }

}
