package me.own.learn.chat.service.impl.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author: yexudong
 * @Date: 2020/1/7 15:08
 */
public class HandshakeInterceptor implements org.springframework.web.socket.server.HandshakeInterceptor {

    //初次握手前
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                                   WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
            String userName = "ws";
            // 使用userName区分WebSocketHandler，以便定向发送消息
            // String userName = (String)
            // session.getAttribute("WEBSOCKET_USERNAME");
            map.put("WEBSOCKET_USERNAME", userName);
            servletRequest.getSession().setAttribute("WEBSOCKET_USERNAME", userName);
        }

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
