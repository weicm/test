package cn.julong.model.factory.abstracted;

/**
 * Created by Think on 2016/7/26.
 */
public class MainTest {
	/**
	 * 抽象工厂模式提供一个创建一系列相关或相互依赖产品对象的接口，而无须指定他们具体的类。它针对的是有多个产品的等级结构。而工厂方法模式针对的是一个产品的等级结构。
	 * @param args
	 */
	public static void main(String[] args) {
		BigFactory bf = new BigFactory();
		SmallFactory sf = new SmallFactory();
		bf.producePhone().run();
		bf.produceHeadset().play();

		sf.producePhone().run();
		sf.produceHeadset().play();
	}
}
