package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateData {
	//��������
			static {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			public static void main(String[] args) {
				try {
					//�������ݿ�����
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/moot?useUnicode=true&characterEncoding=utf8","root","123456");
					//��дsql���
					String sql = "update tb_user set name='�ں㴨',tel='10086' where id=2";
					//�������������
					PreparedStatement smt = conn.prepareStatement(sql);
					//ִ��sql���
					smt.executeUpdate();
					//�ر����ݿ�����
					conn.close();
					System.out.println("�����޸���ϣ�");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
}
