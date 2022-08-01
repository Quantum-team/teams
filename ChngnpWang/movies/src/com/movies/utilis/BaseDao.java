package com.movies.utilis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class BaseDao {
	private static String url="jdbc:mysql:///movies_db?useUnicode=true&characterEncoding=utf8";
	private static String userName="root";
	private static String password="123456";
	private static String driver="com.mysql.jdbc.Driver";
	static{
		try{
			Class.forName(driver);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,userName,password);
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
}
