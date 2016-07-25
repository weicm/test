package cn.julong.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置工具类
 * Created by weicm on 2016/5/31.
 */
public final class Config {
	private static final Properties prop = new Properties();

	static {
		InputStream in = Config.class.getResourceAsStream("/conf.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private Config() {
	}
	/**
	 * 从lantern.common工程的类路径下的conf.properties中获取属性
	 * @param key 属性对应的关键字
	 * @return
	 */
	public static String getProperty(String key) {
		String property = prop.getProperty(key);
		return property;
	}

	/**
	 * 获取properties对象
	 * @return
	 */
	public static Properties getConf() {
		return prop;
	}
	/**
	 * 加载其他配置文件
	 * @param file
	 * @return
	 */
	public static boolean load(String file) {
		if(!file.startsWith("/")) {
			file = "/"+file;
		}
		InputStream in = Config.class.getResourceAsStream(file);
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	/**
	 * 从lantern.common工程的类路径下的指定文件中获取属性
	 * @param file 文件名，相对工程class目录
	 * @return
	 */
	public static Properties getProps(String file) {
		if(!file.startsWith("/")) {
			file = "/"+file;
		}
		InputStream in = Config.class.getResourceAsStream(file);
		Properties pp = new Properties();
		try {
			pp.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pp;
	}

	/**
	 * 从当前工程类路径下的conf.properties文件获取属性
	 * @param clazz
	 * @return
	 */
	public static Properties getProps(Class clazz) {
		return getProps(clazz, "conf.properties");
	}

	/**
	 * 从当前工程类路径下的指定文件获取属性
	 * @param clazz 当前类
	 * @param file 文件名
	 * @return
	 */
	public static Properties getProps(Class clazz, String file) {
		if(null == clazz) {
			return null;
		}
		if(!file.startsWith("/")) {
			file = "/"+file;
		}
		Properties pp = new Properties();
		InputStream in = clazz.getResourceAsStream(file);
		try {
			pp.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pp;
	}

}
