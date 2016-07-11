package cn.julong.blockqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
	private Random random = new Random();

	protected BlockingQueue queue = null;

	public Producer(BlockingQueue queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			while(true) {
				int val = random.nextInt(10);
				System.out.println(Thread.currentThread().getId()+"producer put : " + val);
				queue.put(val);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}  