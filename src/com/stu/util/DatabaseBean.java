package com.stu.util;

import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

public class DatabaseBean {
	public static Connection getConnection() throws SQLException {
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/oracle";
		String userid = "system";
		String password = "123456";
		// Oracle ���ݿ⳧���ṩ��ר�ŵ����ݿ������� ojdbc6.jar
		// ����ʹ�� ORACLE ������Դ OracleDataSource ���������ݿ�����
		// ��ʹ��ʱ��Ҫ������Ӧ�İ�
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
