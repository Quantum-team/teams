package com.personal.Utili;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCutili {
	private static String url="jdbc:mysql:///population?characterEncoding=UTF-8";
	private static String userName="root";
	private static String password="123456";
	private static String driver="com.mysql.jdbc.Driver";
	
	static{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn() throws SQLException{
		return DriverManager.getConnection(url,userName,password);
	}
	
	public static void close(Connection con,PreparedStatement smt,ResultSet rs){
		try{
			if (con!=null) {
				con.close();
			}
			if (smt!=null) {
				smt.close();
			}
			if (rs!=null) {
				rs.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws SQLException {
		System.out.println(getConn());
	}
}
