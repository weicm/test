package cn.julong.model.factory.abstracted;

/**
 * Created by Think on 2016/7/26.
 */
public class BigFactory implements IFactory {

	public MeizuPhone producePhone() {
		return new PRO5();
	}

	public Headset produceHeadset() {
		return new EP30();
	}
}
