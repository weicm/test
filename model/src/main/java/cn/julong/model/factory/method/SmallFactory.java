package cn.julong.model.factory.method;

/**
 * Created by Think on 2016/7/26.
 */
public class SmallFactory implements IFactory {
	public MeizuPhone produce() {
		return new MX5();
	}
}
