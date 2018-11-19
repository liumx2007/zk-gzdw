package com.zzqx.support.framework.websocket;

import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SystemWebSocketHandler implements WebSocketHandler {

    public static List<WebSocketSession> webSocketClients = new CopyOnWriteArrayList<>();

    public void broadcast(String message) {
        webSocketClients.forEach(webSocketSession -> {
            try {
                if(webSocketSession.isOpen()) {
                    webSocketSession.sendMessage(new TextMessage(message));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        webSocketClients.add(webSocketSession);
        System.out.println(webSocketSession.getAttributes().get("id")+" =================");
        System.out.println(new Date()+" 有新的websocket客户端连接["+webSocketSession.getRemoteAddress()+"]，共有"+webSocketClients.size()+"个客户端连接至websocket服务器。");
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        System.out.println(webSocketMessage.getPayload());
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if(webSocketSession.isOpen()) {
            webSocketSession.close();
        }
        if(webSocketClients.contains(webSocketSession)) {
            webSocketClients.remove(webSocketSession);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        if(webSocketClients.contains(webSocketSession)) {
            webSocketClients.remove(webSocketSession);
        }
        System.out.println(new Date()+" 客户端失去连接["+webSocketSession.getRemoteAddress()+"]，还有"+webSocketClients.size()+"个客户端连接至websocket服务器。");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
