package com.movies.utils;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	//驱动，访问路径，用户名，密码
	private static String driver="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/movies_db?characterEncoding=UTF-8";
	private static String user="root";
	private static String password="123456";
	
	//加载驱动
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//建立连接
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user,password);
	}
	
	//关闭连接
	public static void close(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet) {
		try {
			if (connection!=null) {
				connection.close();
			}
			if (preparedStatement!=null) {
				preparedStatement.close();
			}
			if (resultSet!=null) {
				resultSet.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//测试连接
	public static void main(String[] args) throws SQLException {
		System.out.println(getConnection());
	}
}
