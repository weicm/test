package cn.julong.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * sql结果的业务处理通过构造此类的一个临时内部类在wrap方法中实现
 * Created by weicm on 2016/6/3.
 */
public interface ResultWrapper {
	void wrap(ResultSet rs) throws SQLException;
}
