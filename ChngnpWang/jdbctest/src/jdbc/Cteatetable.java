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
			//3、书写sql语句
			String sql = "create table tb_user(\r\n"
					+ "id int primary key auto_increment,\r\n"
					+ "name varchar(20) not null,\r\n"
					+ "password varchar(15) not null,\r\n"
					+ "tel varchar(11) not null\r\n"
					+ ")";
			//4、创建命令管理器
			PreparedStatement smt = conn.prepareStatement(sql);
			// 5、执行sql语句
			smt.executeUpdate();
			//6、关闭数据库链接
			conn.close();
			System.out.println("执行完毕！");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
