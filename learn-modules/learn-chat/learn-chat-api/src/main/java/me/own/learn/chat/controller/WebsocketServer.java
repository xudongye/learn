package me.own.learn.chat.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: yexudong
 * @Date: 2020/1/19 9:49
 */
@ServerEndpoint("/echo/{info}")
public class WebsocketServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketServer.class);

    private static SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//创建时间格式对象
    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketService对象。
    //创建一个房间的集合，用来存放房间
    private static ConcurrentHashMap<String, ConcurrentHashMap<String, WebsocketServer>> roomList =
            new ConcurrentHashMap<String, ConcurrentHashMap<String, WebsocketServer>>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //重新加入房间的标示；
    private int rejoin = 0;

    /**
     * 用户接入
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam(value = "info") String param, Session session) {
        LOGGER.info("连接成功！info:{}", param);

    }

    //加入房间
    public void joinRoom(String member, String user) {
        ConcurrentHashMap<String, WebsocketServer> r = roomList.get(member);
        if (r.get(user) != null) {        //该用户有没有出
            this.rejoin = 1;
        }
        r.put(user, this);//将此用户加入房间中
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 接收到来自用户的消息
     *
     * @param message
     * @param session
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        for (Session openSession : session.getOpenSessions()) {
            openSession.getBasicRemote().sendText(message);
        }
        LOGGER.info("收到消息：{}", message);
    }

    /**
     * 用户断开
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        LOGGER.info("退出连接！,session:{}", session.getId());
    }

    /**
     * 用户连接异常
     *
     * @param t
     */
    @OnError
    public void onError(Throwable t) {
        LOGGER.error("连接异常！reason:{}", t.getMessage());
    }

}
