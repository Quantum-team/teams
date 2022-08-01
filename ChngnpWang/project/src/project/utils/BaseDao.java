package project.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	private static String driver="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/project_db?characterEncoding=UTF-8";
	private static String user="root";
	private static String password="123456";
	
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn() throws SQLException {
		return DriverManager.getConnection(url,user,password);
	}
	
	public static void closse(Connection con,PreparedStatement smt,ResultSet rs) {
		try {
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
