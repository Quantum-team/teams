package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateData {
	//加载驱动
			static {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			public static void main(String[] args) {
				try {
					//建立数据库链接
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/moot?useUnicode=true&characterEncoding=utf8","root","123456");
					//书写sql语句
					String sql = "update tb_user set name='黑恒川',tel='10086' where id=2";
					//创建命令管理器
					PreparedStatement smt = conn.prepareStatement(sql);
					//执行sql语句
					smt.executeUpdate();
					//关闭数据库链接
					conn.close();
					System.out.println("数据修改完毕！");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
}
