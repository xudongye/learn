package me.own.learn.chat.service.impl.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.own.learn.chat.model.ChannelModel;
import me.own.learn.chat.model.ContentModel;
import me.own.learn.chat.model.MessageModel;
import me.own.learn.chat.service.ChatMsgService;
import me.own.learn.chat.service.ChatRoomService;
import me.own.learn.chat.service.ChatUserService;
import me.own.learn.chat.model.Channel;
import me.own.learn.chat.vo.ChatUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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


    private static ConcurrentHashMap<Long, WebsocketServer> webSockets = new ConcurrentHashMap<Long, WebsocketServer>();

    private static Map<String, Long> map = new HashMap<>();

    /**
     * 用户接入
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam(value = "userId") Long userId, Session session) throws JsonProcessingException {
        this.session = session;
        webSockets.put(userId, this);
        ChatUserVo chatUserVo = chatUserService.getById(userId);
        username = chatUserVo.getName();
        map.put(session.getId(), userId);
        String content = username + "进入聊天室！";
        System.out.println("有新的连接，总数：" + webSockets.size() + "  sessionId：" + session.getId() + "  用户" + username);
        MessageModel messageModel = new MessageModel(content, map);

        sendAll(messageModel.toJson());

        refreshChannels(userId);
    }

    private void refreshChannels(long userId) throws JsonProcessingException {
        List<ChannelModel> channelVos = chatMsgService.getChatListByUserId(userId);
        String channels = new ObjectMapper().writeValueAsString(channelVos);
        MessageModel messageModel = new MessageModel(channels, map);
        sendAll(messageModel.toJson());
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
        String content = contentModel.getContent();
        Long from = contentModel.getFrom();
        List<Long> tos = contentModel.getTos();
        MessageModel msg = new MessageModel();
        switch (contentModel.getType()) {
            //text
            case 1:
                LOGGER.info("send text {}", content);
            case 2:
                LOGGER.info("send image url {}", content);
            case 3:
                LOGGER.info("send video url {}", content);
            case 4:
                LOGGER.info("send channel list {}", content);
                msg.setUserIds(map);
                msg.setContent(content);
                //群聊
                if (tos.size() == 0) {
                    tos = new ArrayList<>(webSockets.keySet());
                    sendAll(msg.toJson());
                } else {//单聊
                    tos.add(from);
                    sendOne(msg.toJson(), tos.get(0));
                }
                contentModel.setTos(tos);
                chatMsgService.save(contentModel);
        }
    }

    private void sendAll(String content) {
        for (Long aLong : webSockets.keySet()) {
            try {
                webSockets.get(aLong).session.getBasicRemote().sendText(content);
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    public void sendOne(String content, Long to) {
        try {
            webSockets.get(to).session.getBasicRemote().sendText(content);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
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
        sendAll(message.toJson());
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
