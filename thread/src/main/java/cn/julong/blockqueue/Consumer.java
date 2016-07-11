package cn.julong.blockqueue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

	protected BlockingQueue<Integer> queue = null;

	public Consumer(BlockingQueue queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			while(true) {
				int val = queue.take();
				System.out.println(Thread.currentThread().getId()+"---consumer take : " + val);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}  