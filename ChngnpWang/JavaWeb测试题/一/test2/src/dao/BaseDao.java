package dao;
/*
 * 数据库的操作，查询和删除
 * */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.cart;

public class BaseDao {
	private Connection conn;
	
	private PreparedStatement pstmt;
	
	private ResultSet rs;
	//查询
	public List<cart> queryCart(){
		DBconnection db = new DBconnection();
		try {
			conn= db.getConnection();
			
			String sql = "select * from tb_good";
			
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			List<cart> cartList = new ArrayList<cart>();
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				float price = rs.getFloat("price");
				int count = rs.getInt("count");
				String createTime = rs.getString("createTime");
				String desc = rs.getString("desc");
				cart cart = new cart(id,name,price,count,createTime,desc);
				cartList.add(cart);
			}
			return cartList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	//删除
	public boolean delcart(int id){
		DBconnection db = new DBconnection();
		try {
			conn = db.getConnection();
			String sql = "delete from tb_good where id = ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int i =pstmt.executeUpdate();
			if(i > 0) {
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
