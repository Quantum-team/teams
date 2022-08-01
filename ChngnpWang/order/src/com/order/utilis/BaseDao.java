package com.order.utilis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.bind.ValidationEvent;

public class BaseDao {
	private static String url="jdbc:mysql:///OrderDb?useUnicode=true&amp;characterEncoding=UTF-8&amp;useSSL=false";
	private static String userName="root";
	private static String pasword="123456";
	private static String driver="com.mysql.jdbc.Driver";
	
	static{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,userName,pasword);
	}
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
	public static void main(String[] args) throws SQLException {
		System.out.println(getConnection());
	}
}
