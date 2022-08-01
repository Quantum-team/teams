package dao;
/*
 * ����һ�ű������ɾ���Ĳ�
 * 
 * */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import entity.User;
import untils.DBConnection;

public class UserDao {
	
//	������
	public List<User> queryAll(){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
//		1.��ȡ����
		DBConnection dbc = new DBConnection();
		conn=dbc.getConnection();
		try {		
//			2.����sql
			String sql = "select * from user";
//			3.Ԥ����sql
			pstmt = conn.prepareStatement(sql);
//			4.ִ��
			rs=pstmt.executeQuery();
//			3.���ս����
			List<User> users = new ArrayList<>();
//			�жϽ�������Ƿ�������
			while(rs.next()) {
				int id = rs.getInt("id");
				String name =rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
//				��װ����
				User user = new User();
				user.setId(id);
				user.setName(name);
				user.setAge(age);
				user.setEmail(email);
				users.add(user);
			}
//			����List
			return users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbc.close(conn, null, rs);
		}
		return null;
	}
	//ɾ��
	public boolean deleteById(int id) {
		Connection conn=null;
		PreparedStatement pstmt=null;
//		1.��ȡ����
		DBConnection dbc = new DBConnection();
		conn=dbc.getConnection();
		try {
			//����sql,?����һ��ռλ��
			String sql = "delete from user where id = ?";
			//����sql
			pstmt = conn.prepareStatement(sql);
			//�滻ռλ��
			pstmt.setInt(1, id);
			int i = pstmt.executeUpdate();
			if (i>0) {
				return true;
			}else {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
//	��ӷ���
	public boolean addUser(User user) {
		Connection conn=null;
		PreparedStatement pstmt=null;
//		1.��ȡ����
		DBConnection dbc = new DBConnection();
		conn=dbc.getConnection();
		try {
			//����sql,?����һ��ռλ��
			String sql = "insert into user values(?,?,?,?)";
			//����sql
			pstmt = conn.prepareStatement(sql);
			//�滻ռλ��
			pstmt.setInt(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setInt(3, user.getAge());
			pstmt.setString(4, user.getEmail());
			
			int i = pstmt.executeUpdate();
			if (i>0) {
				return true;
			}else {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
//	���·���
	public boolean updateById(User user) {
		Connection conn=null;
		PreparedStatement pstmt=null;
//		1.��ȡ����
		DBConnection dbc = new DBConnection();
		conn=dbc.getConnection();
		try {
			//����sql,?����һ��ռλ��
			String sql = "update user set name=?,age=?,email=? where id = ? ";
			//����sql
			pstmt = conn.prepareStatement(sql);
			//�滻ռλ��
			pstmt.setString(1, user.getName());
			pstmt.setInt(2, user.getAge());
			pstmt.setString(3, user.getEmail());
			pstmt.setInt(4, user.getId());
			
			int i = pstmt.executeUpdate();
			if (i>0) {
				return true;
			}else {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
