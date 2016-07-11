package cn.julong.delayqueue;

import java.util.concurrent.DelayQueue;

class Teacher implements Runnable {


	private DelayQueue<Student> students;

	public Teacher(DelayQueue<Student> students) {
		this.students = students;
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println(" test start");
			while (!Thread.interrupted()) {
				students.take().run();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}