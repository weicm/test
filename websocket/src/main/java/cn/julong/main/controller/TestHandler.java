package cn.julong.main.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by Think on 2016/7/21.
 */
@Component
public class TestHandler extends TextWebSocketHandler {
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		session.sendMessage(new TextMessage("hi, " +message.getPayload().toString()));
	}
}
