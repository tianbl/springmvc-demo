package com.demo.websocket;


import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by baolei on 2016/4/8.
 */
public class WebsocketEndPoint extends TextWebSocketHandler implements WebSocketHandler {

    public WebsocketEndPoint(){
        System.out.println("*******************************************************");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) throws Exception {
        System.out.println("保存session以备推送信息使用");
        WebSocketSessionContainer.session = session;
        super.handleTextMessage(session, message);
        TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server");
        session.sendMessage(returnMessage);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        WebSocketSessionContainer.session = webSocketSession;
        System.out.println(">>>>>>>>>> afterConnectionEstablished");
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        System.out.println(">>>>>>>>>> handleMessage");
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        System.out.println(">>>>>>>>>> handleTransportError");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        System.out.println(">>>>>>>>>> afterConnectionClosed");
    }

    @Override
    public boolean supportsPartialMessages() {
        System.out.println(">>>>>>>>>> supportsPartialMessages");
        return false;
    }
}
