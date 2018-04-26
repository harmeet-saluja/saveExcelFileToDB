package com.yash.excelfiledemo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtil {
	private static PreparedStatement pstmt = null;
	private static Connection connection = null;

	static {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private DBUtil() {

	}

	public static PreparedStatement getPreparedStatement(String sql) {
		try {
			pstmt = connection.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return pstmt;
	}

	public static void close() {
		try {
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
}
