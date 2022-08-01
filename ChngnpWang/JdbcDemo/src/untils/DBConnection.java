package untils;
/**
 * ���ݿ�����ӚG���ر�
 * 
 * */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class DBConnection {
	
	
	
//	1.��������
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	2.���ӷ���
	public static Connection getConnection() {
		Connection conn=null;
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf-8", "root", "123456");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
//	3.�ر�
	public static void close(Connection conn,PreparedStatement pstmt,ResultSet rs) {
			try {
				if (rs!=null) {
					rs.close();
				}
				if (pstmt!=null) {
					pstmt.close();
				}
				if (conn!=null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

//	public static void main(String[] args) {
//		System.out.println(getConnection());
//	}
}
