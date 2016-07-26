package cn.julong.model.factory.method;

/**
 * Created by Think on 2016/7/26.
 */
public class MainTest {
	/**
	 * 工厂方法模式是有一个抽象的工厂父类定义公共接口，工厂子类负责生成具体的产品对象，这样做的目的是将产品类的实例化操作延迟到工厂子类中完成。
	 * @param args
	 */
	public static void main(String[] args) {
		BigFactory bf = new BigFactory();
		SmallFactory sf = new SmallFactory();
		bf.produce().run();
		sf.produce().run();
	}
}
