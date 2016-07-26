package cn.julong.model.factory.abstracted;

/**
 * Created by Think on 2016/7/26.
 */
public class SmallFactory implements IFactory {

	public MeizuPhone producePhone() {
		return new MX5();
	}

	public Headset produceHeadset() {
		return new EP21();
	}
}
