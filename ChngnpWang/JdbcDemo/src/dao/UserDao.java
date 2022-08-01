package dao;
/*
 * 定义一张表的增，删，改查
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
	
//	查所有
	public List<User> queryAll(){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
//		1.获取连接
		DBConnection dbc = new DBConnection();
		conn=dbc.getConnection();
		try {		
//			2.定义sql
			String sql = "select * from user";
//			3.预处理sql
			pstmt = conn.prepareStatement(sql);
//			4.执行
			rs=pstmt.executeQuery();
//			3.接收结果集
			List<User> users = new ArrayList<>();
//			判断结果集中是否还有数据
			while(rs.next()) {
				int id = rs.getInt("id");
				String name =rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
//				封装对象
				User user = new User();
				user.setId(id);
				user.setName(name);
				user.setAge(age);
				user.setEmail(email);
				users.add(user);
			}
//			返回List
			return users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbc.close(conn, null, rs);
		}
		return null;
	}
	//删除
	public boolean deleteById(int id) {
		Connection conn=null;
		PreparedStatement pstmt=null;
//		1.获取连接
		DBConnection dbc = new DBConnection();
		conn=dbc.getConnection();
		try {
			//定义sql,?代表一个占位符
			String sql = "delete from user where id = ?";
			//处理sql
			pstmt = conn.prepareStatement(sql);
			//替换占位符
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
//	添加方法
	public boolean addUser(User user) {
		Connection conn=null;
		PreparedStatement pstmt=null;
//		1.获取连接
		DBConnection dbc = new DBConnection();
		conn=dbc.getConnection();
		try {
			//定义sql,?代表一个占位符
			String sql = "insert into user values(?,?,?,?)";
			//处理sql
			pstmt = conn.prepareStatement(sql);
			//替换占位符
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
	
//	更新方法
	public boolean updateById(User user) {
		Connection conn=null;
		PreparedStatement pstmt=null;
//		1.获取连接
		DBConnection dbc = new DBConnection();
		conn=dbc.getConnection();
		try {
			//定义sql,?代表一个占位符
			String sql = "update user set name=?,age=?,email=? where id = ? ";
			//处理sql
			pstmt = conn.prepareStatement(sql);
			//替换占位符
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
