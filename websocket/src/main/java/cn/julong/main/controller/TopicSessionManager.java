package cn.julong.main.controller;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Think on 2016/7/8.
 */
@Component
public class TopicSessionManager implements ApplicationListener<AbstractSubProtocolEvent> {

	private static final Map<String, ArrayList<String>> topicSessions = new HashMap<String, ArrayList<String>>();

	public void onApplicationEvent(AbstractSubProtocolEvent event) {

		StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());


		System.out.println(event);
	}
}
