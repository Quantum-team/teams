package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


//添加数据
public class InsertData {
	//加载驱动
	static{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		//建立数据库链接
		try{
			Connection connection=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/moot?useUnicode=true&characterEncoding=utf8","root","123456");
			//书写sql语句
			String sql="insert into tb_user values"
					+ "(0,'张三','123456','17488071018'),"
					+ "(0,'李四','123456','17488071019'),"
					+ "(0,'王五','123456','17488071020')";
			//创建命令管理器
			PreparedStatement smt=connection.prepareStatement(sql);
			//执行sql语句
			smt.execute();
			//关闭数据库链接
			connection.close();
			System.out.println("添加成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
