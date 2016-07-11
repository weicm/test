package cn.julong.jdbc;


import cn.julong.conf.Config;

/**
 * 驱动类型
 * Created by weicm on 2016/6/3.
 */
public enum DBType {
	SPARK("spark.url", "spark.driver"),
	HBASE("hbase.url", "hbase.driver");

	private String url;
	private String driver;
	private DBType(String url, String driver) {
		this.url = Config.getProperty(url);
		this.driver = Config.getProperty(driver);
	}

	public String getUrl() {
		return url;
	}
	public String getDriver() {
		return driver;
	}
}
