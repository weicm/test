package cn.julong.main.controller;

import cn.julong.main.bean.Hi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.handler.WebSocketSessionDecorator;

import java.util.ArrayList;

/**
 * Created by Think on 2016/7/8.
 */
@Controller
@RequestMapping("/mvc")
public class MyController{

	private SimpMessagingTemplate template;

	private Thread task = null;

	private boolean run = false;

	private static int count = 0;

	@Autowired
	public MyController(SimpMessagingTemplate template) {
		this.template = template;
	}

	@MessageMapping("/hi")
	public void greeting(final Hi hi) throws Exception {
		run = "start".equals(hi.getName()) ? true : false;
		if(run) {
			if(null != task) {
				return;
			} else {
				task = new Thread() {
					@Override
					public void run() {
						while (run) {
							template.convertAndSend("/topic/hello", "{\"num\": \""+ hi.getName() + MyDecorator.getSessions().size() +"\"}");
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {e.printStackTrace();}
						}
					}
				};
				task.start();
			}
		} else {
			if(null != task) {
				task = null;
			}
		}

	}

	@RequestMapping("/test")
	@ResponseBody
	public Object test() {
		System.out.println("is running...........");

		return "test ......";

	}
}
