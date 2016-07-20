package cn.julong.main.controller;

import cn.julong.main.bean.Hi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
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
	public void greeting(final Hi hi, SimpMessageHeaderAccessor headerAccessor) throws Exception {
		System.out.println(headerAccessor);
		System.out.println(headerAccessor.getDestination()+"--"+headerAccessor.getSubscriptionId());

		run = "start".equals(hi.getName()) ? true : false;
		if(run) {
			if(null != task) {
				return;
			} else {
				task = new Thread() {
					@Override
					public void run() {
						while (run) {
							template.convertAndSend("/topic/hello", "{\"num\": \""+ hi.getName() +"\"}");
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
