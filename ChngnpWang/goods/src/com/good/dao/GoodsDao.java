package com.good.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.good.model.Goods;
import com.good.utilis.BaseDao;

public class GoodsDao {
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	
	public List<Goods> select(String goodName) throws SQLException{
		connection=BaseDao.getConnection();
		String sql=null;
		if (goodName!=null) {
			sql="select * from tb_goods where goodName like '%"+goodName+"%'";
		}else {
			sql="select * from tb_goods";
		}
		preparedStatement=connection.prepareStatement(sql);
		resultSet=preparedStatement.executeQuery();
		List<Goods> list=new ArrayList<Goods>();
			while(resultSet.next()){
			Goods goods=new Goods();
			goods.setGoodId(resultSet.getInt("goodId"));
			goods.setGoodName(resultSet.getString("goodName"));
			goods.setNum(resultSet.getInt("num"));
			goods.setPrice(resultSet.getDouble("price"));
			list.add(goods);
		}
		BaseDao.close(connection, preparedStatement, resultSet);
		return list;
	}
	
	public int insert(Goods goods) throws SQLException{
		connection=BaseDao.getConnection();
		String sql="insert into tb_goods values(0,?,?,?)";
		try{
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, goods.getGoodName());
			preparedStatement.setInt(2, goods.getNum());
			preparedStatement.setDouble(3, goods.getPrice());
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			BaseDao.close(connection, preparedStatement, resultSet);
		}
		return 0;
	}
}
