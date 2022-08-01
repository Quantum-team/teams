package com.library.utilis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	private static String url="jdbc:mysql://localhost:3306/library_db?useUnicode=true&amp;characterEncoding=UTF-8&amp;useSSL=false";
	private static String userName="root";
	private static String password="123456";
	// Msqyl 5.0 驱动
	private static String driver="com.mysql.jdbc.Driver";
	// 加载驱动
	static{
		try{
			Class.forName(driver);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 连接
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,userName,password);
	}
//	public static void main(String[] args) throws SQLException {
//		System.out.println(getConnection());
//	}
	//关闭
	public static void close(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet){
		try{
			if (connection!=null) {
				connection.close();
			}
			if (preparedStatement!=null) {
				preparedStatement.close();
			}
			if (resultSet!=null) {
				resultSet.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
