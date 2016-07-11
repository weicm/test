package cn.julong.jdbc;

import java.sql.*;
import java.util.List;

/**
 * sql帮助类
 * Created by weicm on 2016/6/3.
 */
public class SQLHelper {
	private Connection conn = null;

	/**
	 * 使用示例
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {

		//更新
		SQLHelper.getInstance(DBType.HBASE).update("update ...");
		//查询
		SQLHelper.getInstance(DBType.HBASE).query("select ...", new ResultWrapper() {
			public void wrap(ResultSet rs) throws SQLException {
				//这里实现结果处理逻辑
				while (rs.next()) {
					int anInt = rs.getInt(1);
					String string = rs.getString(2);
					System.out.println(anInt+"--"+string);
				}
			}
		});
	}

	/**
	 * 批量执行insert、delete、update
	 * @param sqls 要执行的sql
	 * @return
	 */
	public int[] executeBatch(String[] sqls) {
		Statement st = null;
		int[] results = null;
		try {
			st = conn.createStatement();
			for(int i=0; i<sqls.length; i++) {
				st.addBatch(sqls[i]);
			}
			results = st.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.free(conn, st, null);
		}
		return results;
	}
	/**
	 * prepare更新
	 * @param sql 要执行的sql
	 * @param params sql中的参数列表
	 * @return
	 */
	public int prepareUpdate(String sql, List<Object> params) {
		PreparedStatement st = null;
		int result = -1;
		try {
			st = conn.prepareStatement(sql);
			for(int i=1; i<=params.size(); i++) {
				st.setObject(i, params.get(i-1));
			}
			result = st.executeUpdate(sql);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.free(conn, st, null);
		}
		return result;
	}
	/**
	 * 更新
	 * @param sql 要执行的sql
	 * @return
	 */
	public int update(String sql) {
		Statement st = null;
		int result = -1;
		try {
			st = conn.createStatement();
			result = st.executeUpdate(sql);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.free(conn, st, null);
		}
		return result;
	}
	/**
	 * prepare查询
	 * @param sql 将要执行的sql
	 * @param params sql中参数列表
	 * @param wrapper 结果回调类
	 */
	public void prepareQuery(String sql, List<Object> params, ResultWrapper wrapper) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(sql);
			for(int i=1; i<=params.size(); i++) {
				st.setObject(i, params.get(i-1));
			}
			rs = st.executeQuery(sql);
			wrapper.wrap(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.free(conn, st, rs);
		}
	}

	/**
	 * 普通查询
	 * @param sql 将要执行的sql
	 * @param wrapper 结果回调类
	 */
	public void query(String sql, ResultWrapper wrapper) {
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			wrapper.wrap(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.free(conn, st, rs);
		}
	}

	private SQLHelper() {}
	public static SQLHelper getInstance(DBType dbType) {
		SQLHelper sqlHelper = new SQLHelper();
		sqlHelper.setConn(DBUtil.getConnection(dbType));
		return sqlHelper;
	}
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
