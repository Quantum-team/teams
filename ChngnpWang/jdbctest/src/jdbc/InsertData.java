package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


//�������
public class InsertData {
	//��������
	static{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		//�������ݿ�����
		try{
			Connection connection=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/moot?useUnicode=true&characterEncoding=utf8","root","123456");
			//��дsql���
			String sql="insert into tb_user values"
					+ "(0,'����','123456','17488071018'),"
					+ "(0,'����','123456','17488071019'),"
					+ "(0,'����','123456','17488071020')";
			//�������������
			PreparedStatement smt=connection.prepareStatement(sql);
			//ִ��sql���
			smt.execute();
			//�ر����ݿ�����
			connection.close();
			System.out.println("��ӳɹ�");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
