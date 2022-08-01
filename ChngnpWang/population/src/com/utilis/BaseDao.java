package com.utilis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	
	private static String url="jdbc:mysql://localhost:3306/population_db?characterEncoding=UTF-8";
	private static String userName="root";
	private static String password="123456";
	private static String driver="com.mysql.jdbc.Driver";
	
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,userName,password);
	}
	public static void colse(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet){
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
