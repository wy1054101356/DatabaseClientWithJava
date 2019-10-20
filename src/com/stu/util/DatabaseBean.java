package com.stu.util;

import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

public class DatabaseBean {
	public static Connection getConnection() throws SQLException {
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/oracle";
		String userid = "system";
		String password = "123456";
		// Oracle 数据库厂商提供了专门的数据库驱动包 ojdbc6.jar
		// 这里使用 ORACLE 的数据源 OracleDataSource 来创建数据库连接
		// 在使用时需要引入相应的包
		OracleDataSource ds = new OracleDataSource();
		ds.setURL(jdbcUrl);
		return ds.getConnection(userid, password);
	}

	public static void close(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
