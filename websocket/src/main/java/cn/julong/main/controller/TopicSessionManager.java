package cn.julong.main.controller;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Think on 2016/7/8.
 */
@Component
public class TopicSessionManager {

	private static final Map<String, ArrayList<String>> topicSessions = new HashMap<String, ArrayList<String>>();

	@EventListener
	private void handleSessionSubscribe(SessionSubscribeEvent event) {

		StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
		String id = sha.getSessionId();
		String topic = sha.getDestination();
		//获取topicid，不存在，则创建当前topicid
		ArrayList<String> sessions = topicSessions.get(topic);
		System.out.println("before add: "+sessions);
		if(null == sessions) {
			sessions = new ArrayList<String>();
			topicSessions.put(topic, sessions);
		}
		//存在:判断session是否已经存在，存在则不处理（避免同一sessiond的多次事件）,不存在则加入
		if(!sessions.contains(id)) {
			sessions.add(id);
		}
		System.out.println("after add: "+sessions);
	}
	@EventListener
	private void handleSessionUnsubscribe(SessionUnsubscribeEvent event) {
		StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
		String id = sha.getSessionId();
		String topic = sha.getSubscriptionId();
		//判断topic是否存在，存在才删除
		ArrayList<String> sessions = topicSessions.get(topic);
		System.out.println("before delete: "+sessions);
		if(null != sessions) {
			//存在id才删除，避免同一session多次事件
			if(sessions.contains(id)) {
				sessions.remove(id);
			}
		}
		System.out.println("after delete: "+sessions);
	}
	@EventListener
	private void handleSessionDisconnect(SessionDisconnectEvent event) {
		StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
		String id = sha.getSessionId();
		Iterator<Map.Entry<String, ArrayList<String>>> iti = topicSessions.entrySet().iterator();
		while (iti.hasNext()) {
			ArrayList<String> ids = iti.next().getValue();
			if(ids.contains(id)) {
				ids.remove(id);
			}
		}
	}
}
