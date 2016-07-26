package cn.julong.model.factory.simple;

/**
 * Created by Think on 2016/7/26.
 */
public class MainTest {
	/**
	 * 简单工厂模式是由一个具体的类去创建其他类的实例，父类是相同的，父类是具体的。
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Factory f = new Factory();
		f.produce("PRO5").run();
		f.produce("PRO6").run();
	}
}
