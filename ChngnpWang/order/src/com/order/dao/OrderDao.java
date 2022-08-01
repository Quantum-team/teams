package com.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.order.model.Order;
import com.order.utilis.BaseDao;

public class OrderDao {
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	
	//²éÑ¯
	public List<Order> seleteAll() throws SQLException{
		connection=BaseDao.getConnection();
		String sql="select * from tb_order";
		preparedStatement=connection.prepareStatement(sql);
		resultSet=preparedStatement.executeQuery();
		List<Order> list=new ArrayList<Order>();
		if (resultSet!=null) {
			while (resultSet.next()) {
				Order order=new Order();
				order.setId(resultSet.getInt("id"));
				order.setName(resultSet.getString("name"));
				order.setPrice(resultSet.getDouble("price"));
				order.setOrderId(resultSet.getString("orderId"));
				order.setDescinfo(resultSet.getString("descinfo"));
				list.add(order);
			}
			return list;
		}else {
			BaseDao.close(connection, preparedStatement, resultSet);
		} 
		return null;
	}
	
	//É¾³ý
	public int deleteOrder(int id) throws SQLException{
		connection=BaseDao.getConnection();
		try{
		String sql="delete from tb_order where id=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			BaseDao.close(connection, preparedStatement, resultSet);
		}
		return 0;
	}
	
	//Ìí¼Ó
	public int  addOrder(Order order) throws SQLException {
		connection=BaseDao.getConnection();
		String sql="insert into tb_order values(0,?,?,?,?)";
		try{
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, order.getName());
			preparedStatement.setDouble(2, order.getPrice());
			preparedStatement.setString(3, order.getOrderId());
			preparedStatement.setString(4, order.getDescinfo());
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			BaseDao.close(connection, preparedStatement, resultSet);
		}
		return 0;
	}
}
