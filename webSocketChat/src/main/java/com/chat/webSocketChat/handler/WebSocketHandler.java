package com.chat.webSocketChat.handler;

import com.chat.webSocketChat.vo.Message;
import com.google.gson.Gson;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketHandler extends TextWebSocketHandler {
    // Map<세션 ID, 세션>
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<String, WebSocketSession>();

    // 웹 소켓 연결
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 세션 저장
        String sessionId = session.getId();
        sessions.put(sessionId, session);

        // VO setting
        Message message = new Message();
        message.setSender(sessionId);
        message.setReceiver("all");

        // 모든 세션에 접속 알림
        for (WebSocketSession webSession : sessions.values()) {
            if (!webSession.getId().equals(sessionId)) {
                webSession.sendMessage(new TextMessage(message.toString()));
            }
        }
    }

    // 양방향 데이터 통신
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        // Gson 라이브러리를 사용하여 json -> 객체 변환
        Gson gson = new Gson();
        Message message = gson.fromJson(textMessage.getPayload(), Message.class);
        message.setSender(session.getId());

        for (WebSocketSession webSession : sessions.values()) {
            webSession.sendMessage(new TextMessage(message.toString()));
        }
    }

    // 소켓 연결 종료
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String sessionId = session.getId();

        sessions.remove(sessionId);

        Message message = new Message();
        message.closeConnect();
        message.setSender(sessionId);

        for (WebSocketSession webSession : sessions.values()) {
            webSession.sendMessage(new TextMessage(message.toString()));
        }
    }

    // 소켓 통신 에러
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // 생략
    }
}
