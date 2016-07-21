package cn.julong.main.controller;

import cn.julong.main.bean.Hi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;

/**
 * Created by Think on 2016/7/8.
 */
@Controller
@RequestMapping("/mvc")
public class MyController {

	private SimpMessagingTemplate template;

	private Thread task = null;

	private boolean run = false;

	private static int count = 0;

	@Autowired
	public MyController(SimpMessagingTemplate template) {
		this.template = template;
	}

	@MessageMapping("/hi")
	@SendTo("/topic/hello")
	public String greeting(final String message) throws Exception {
		System.out.println(message);
		return "hi, " + message;
	}

	@RequestMapping("/test")
	@ResponseBody
	public Object test() {
		System.out.println("is running...........");

		return "test ......";

	}
}
