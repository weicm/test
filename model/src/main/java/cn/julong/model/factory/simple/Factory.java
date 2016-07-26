package cn.julong.model.factory.simple;

/**
 * Created by Think on 2016/7/26.
 */
public class Factory {
	public MeizuPhone produce(String name) throws Exception {
		if("PRO5".equals(name)) {
			return new PRO5();
		}else if("PRO6".equals(name)) {
			return new PRO6();
		}else {
			throw new Exception("There is not this kind of phone!");
		}
	}
}
