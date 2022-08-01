package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import utils.BaseDao;

public class Dao {
	Connection conn =null;
	PreparedStatement smt =null;
	ResultSet rs =null;
	
	public List<Order> select(String name) throws SQLException {
//		1数据库连接
		conn=BaseDao.getConnection();
//		2书写sql语句
		String sql=null;
		if (name!=null) {
			sql="select * from `order` where name like '%"+name+"%'";
		}else {
			sql ="SELECT * FROM `order`";
		}
//		3命令管理器
			smt = conn.prepareStatement(sql);
//			4执行sql语句
			rs = smt.executeQuery();
//			4处理结果集
			List<Order> list = new ArrayList<Order>();
				while(rs.next()) {
					Order o = new Order();
					o.setId(rs.getInt("id"));
					o.setOdd(rs.getInt("odd"));
					o.setName(rs.getString("name"));
					o.setOrder(rs.getString("order"));
					o.setQuantity(rs.getInt("quantity"));
					o.setTelephone(rs.getInt("telephone"));
					o.setAddress(rs.getString("address"));
					o.setNote(rs.getString("note"));
					o.setDate(rs.getDate("date"));
					list.add(o);
				}
				//6关闭数据库			
			BaseDao.closeconn(conn, smt, rs);
			return list;
//			
	}
	
	public int delete (int id) throws SQLException {
//		1数据库连接
		conn = BaseDao.getConnection();
//		2书写sql语句
		String sql ="delete from `order` where id=?";
//		3命令管理器
		try {
			smt = conn.prepareStatement(sql);
//			4赋值
			smt.setInt(1, id);
//			5执行sql语句
			return smt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
//			6关闭数据库
			BaseDao.closeconn(conn, smt, rs);
		}
		return 0;
	}
	
	public int add(Order m) throws SQLException {
//		1数据库连接
		conn = BaseDao.getConnection();
//		2书写sql语句
		String sql ="insert into `order` values(0,?,?,?,?,?,?,?,?)";
//		3命令管理器
		try {
			smt = conn.prepareStatement(sql);
//			4赋值
			smt.setInt(1, m.getOdd());
			smt.setString(2, m.getName());
			smt.setString(3, m.getOrder());
			smt.setInt(4, m.getQuantity());
			smt.setInt(5, m.getTelephone());
			smt.setString(6, m.getAddress());
			smt.setString(7, m.getNote());
			smt.setDate(8, new Date(m.getDate().getTime()));
//			5执行sql语句
			return smt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
//			6关闭数据库	
			BaseDao.closeconn(conn, smt, rs);
		}
		return 0;
	}
}
