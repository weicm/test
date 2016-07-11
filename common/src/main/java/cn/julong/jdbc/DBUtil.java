package cn.julong.jdbc;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import java.io.IOException;
import java.sql.*;

/**
 * JDBC工具
 * Created by weicm on 2016/6/3.
 */
public class DBUtil {
	private DBUtil() {}

	/**
	 * 根据驱动类型获取连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection(DBType dbType) {
		Connection conn = null;
		try {
			String url = dbType.getUrl();
			String driver = dbType.getDriver();
			String user = null;
			String passwd = null;
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 释放资源
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public static void free(Connection conn, Statement st, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (st != null) {
					try {
						st.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						if (conn != null) {
							try {
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 把ResultSet转成json对象，包括两部分：元数据、数据
	 * @param rs
	 * @return
	 */
	public static String rs2Json(ResultSet rs) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode json = mapper.createObjectNode();
		ObjectNode jmeta = mapper.createObjectNode();
		ArrayNode jdata = mapper.createArrayNode();
		if(null == rs) {
			return null;
		}
		ResultSetMetaData rmeta = null;
		try {
			rmeta = rs.getMetaData();
			for (int i=1; i<=rmeta.getColumnCount(); i++) {
				jmeta.put(rmeta.getColumnName(i), rmeta.getColumnTypeName(i));
			}
			while (rs.next()) {
				ObjectNode obj = mapper.createObjectNode();
				for(int j=1; j<=rmeta.getColumnCount(); j++) {
					obj.put(rmeta.getColumnName(j), mapper.valueToTree(rs.getObject(j)));
				}
				jdata.add(obj);
			}
			json.put("meta", jmeta);
			json.put("data", jdata);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return json.toString();
	}
}
