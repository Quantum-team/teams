package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteData {
	static{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		try{
			Connection connection=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/moot?useUnicode=true&characterEncoding=utf8","root","123456");
			String sql="delete from tb_user where id=3";
			PreparedStatement smt=connection.prepareStatement(sql);
			smt.execute();
			connection.close();
			System.out.println("");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
