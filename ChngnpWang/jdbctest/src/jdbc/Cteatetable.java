package jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cteatetable {
	static{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		try {
			java.sql.Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/moot?useUnicode=true&characterEncoding=utf8","root","123456");
			//3����дsql���
			String sql = "create table tb_user(\r\n"
					+ "id int primary key auto_increment,\r\n"
					+ "name varchar(20) not null,\r\n"
					+ "password varchar(15) not null,\r\n"
					+ "tel varchar(11) not null\r\n"
					+ ")";
			//4���������������
			PreparedStatement smt = conn.prepareStatement(sql);
			// 5��ִ��sql���
			smt.executeUpdate();
			//6���ر����ݿ�����
			conn.close();
			System.out.println("ִ����ϣ�");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
