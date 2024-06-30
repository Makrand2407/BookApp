package com.BookApp.Util;

import java.sql.*;

public class DbConnection {
	private static String url = "jdbc:mysql://localhost:3306/BookApp";
	private static String username = "root";
	private static String password = "Makrand@123";
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static Connection con;
	private DbConnection() {

	} 
	public static Connection getConnection() {
		try {
			if (con == null || con.isClosed()) {
				Class.forName(driver);
				con = DriverManager.getConnection(url, username, password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
