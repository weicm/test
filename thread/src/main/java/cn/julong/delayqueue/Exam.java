package cn.julong.delayqueue;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.DelayQueue;

public class Exam {


	/**
	 * 业务场景一：多考生考试
	 * 该场景来自于http://ideasforjava.iteye.com/blog/657384，模拟一个考试的日子，考试时间为120分钟，30分钟后才可交卷，当时间到了，或学生都交完卷了考试结束。
	 * 这个场景中几个点需要注意：
	 * 考试时间为120分钟，30分钟后才可交卷，初始化考生完成试卷时间最小应为30分钟
	 * 对于能够在120分钟内交卷的考生，如何实现这些考生交卷
	 * 对于120分钟内没有完成考试的考生，在120分钟考试时间到后需要让他们强制交卷
	 * 在所有的考生都交完卷后，需要将控制线程关闭
	 *
	 * 实现思想：
	 * 用DelayQueue存储考生（Student类），每一个考生都有自己的名字和完成试卷的时间，Teacher线程对DelayQueue进行监控，收取完成试卷小于120分钟的学生的试卷。
	 * 当考试时间120分钟到时，先关闭Teacher线程，然后强制DelayQueue中还存在的考生交卷。
	 * 每一个考生交卷都会进行一次countDownLatch.countDown()，当countDownLatch.await()不再阻塞说明所有考生都交完卷了，而后结束考试。
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		int studentNumber = 20;
		CountDownLatch countDownLatch = new CountDownLatch(studentNumber + 1);
		DelayQueue<Student> students = new DelayQueue<Student>();
		Random random = new Random();
		for (int i = 0; i < studentNumber; i++) {
			students.put(new Student("student" + (i + 1), 30 + random.nextInt(120), countDownLatch));
		}
		Thread teacherThread = new Thread(new Teacher(students));
		students.put(new EndExam(students, 120, countDownLatch, teacherThread));
		teacherThread.start();
		countDownLatch.await();
		System.out.println(" 考试时间到，全部交卷！");
	}


}