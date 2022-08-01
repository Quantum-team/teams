package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	private static String url="jdbc:mysql://localhost:3306/order?characterEncoding=UTF-8";
	private static String driver="com.mysql.jdbc.Driver";
	private static String user="root";
	private static String password="123456";
	
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,user,password);
	}
	
	public static void closeconn(Connection conn,PreparedStatement smt,ResultSet rs) {
		try {
			if (conn!=null) {
				conn.close();
			}
			if (smt!=null) {
				smt.close();
			}
			if (rs!=null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getConnection());
	}
}
