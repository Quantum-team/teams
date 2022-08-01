package com.personal.utilis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	    //连接数据库的路径
//		private static String url="jdbc:mysql://localhost:3306/personal";
		private static String url="jdbc:mysql://localhost:3306/personal?characterEncoding=UTF-8";
		//用户名
		private static String userName="root";
		//密码 
		private static String password="123456";
		//驱动
		private static String driver="com.mysql.jdbc.Driver";
		//加载驱动
		static{
			try{
				Class.forName(driver);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		//建立数据库连接
		public static Connection getConnect() throws SQLException {
			return DriverManager.getConnection(url,userName,password);
		}
		//关闭数据库连接
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
		//测试连接
		public static void main(String[] args) throws SQLException {
			System.out.println(BaseDao.getConnect());
		}
}
