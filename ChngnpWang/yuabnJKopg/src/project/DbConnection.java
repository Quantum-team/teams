package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	//驱动类的类名
	private static final String DRIVERNAME="com.mysql.jdbc.Driver";
	//连接数据的URL路径
	private static final String URL="jdbc:mysql://localhost:3306/db_Storage";
	//数据库登录账号
	private static final String USER="root";
	//数据库登录密码
	private static final String PASSWORD="123456";
	//1.加载驱动，驱动仅需加载一次即可
	static{
		try {
			Class.forName(DRIVERNAME);
		} catch (ClassNotFoundException e) {
			    
			    e.printStackTrace();
		}
	}	
	//获取数据库连接
	public static Connection getConnection() throws Exception  {
		try {
			return DriverManager.getConnection(URL, USER,PASSWORD);
		} catch (SQLException e) {	
			e.printStackTrace();
			throw new Exception();	
		}
	}
	//关闭连接
	public  static  void colse(ResultSet rs,Statement stat,Connection conn)throws Exception{
	   try {
		   if (rs != null) {
			   rs.close();
	       }
		   if (stat != null) {
			   stat.cancel();
		   }
		   if (conn != null) {
			  conn.close();
		   }
	   } catch (Exception e) {
		   e.printStackTrace();
		   throw new Exception();
	   }
	}
//	public static void main(String[] args) throws Exception {
//		DbConnection conn = new DbConnection();
//		System.out.println(conn.getConnection());
//	}
}
