package cn.julong.main.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

import java.util.ArrayList;

/**
 * Created by Think on 2016/7/8.
 */
@Component
public class MyDecorator extends WebSocketHandlerDecorator{


	private static ArrayList<WebSocketSession> sessions = new ArrayList<WebSocketSession>();

	public MyDecorator(WebSocketHandler delegate) {
		super(delegate);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		sessions.remove(session);
	}

	public static ArrayList<WebSocketSession> getSessions() {
		return sessions;
	}

	public static void setSessions(ArrayList<WebSocketSession> sessions) {
		MyDecorator.sessions = sessions;
	}
}
