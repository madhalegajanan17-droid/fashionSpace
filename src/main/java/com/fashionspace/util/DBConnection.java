package com.fashionspace.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {




	private static final String LOCAL_URL = "jdbc:mysql://localhost:3306/fashionspace?useSSL=false";
	private static final String LOCAL_USER = "root";
	private static final String LOCAL_PASS = "Gajanan@017";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("mysql driver not found");
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		Connection con = null;

		con = DriverManager.getConnection(LOCAL_URL, LOCAL_USER, LOCAL_PASS);

		return con;
	}
}


