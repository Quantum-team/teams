package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Persional;
import com.utilis.BaseDao;

public class Dao {
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	public List<Persional> select(String city) throws SQLException{
		 connection=BaseDao.getConnection();
		String sql=null;
		if (city!=null) {
//			sql="select count(0) from tb_population where city like '%昆明%'";
			sql="select  * from tb_population where city like '%"+city+"%'"; // where id =
//			sql="select * from tb_population";
		}else {
			sql="select * from tb_population";
		}
		 preparedStatement=connection.prepareStatement(sql);
//		preparedStatement.setString(1, "昆明市");
		 resultSet=preparedStatement.executeQuery(sql);
		List<Persional> list=new ArrayList<Persional>();
			while (resultSet.next()) {
				Persional persional=new Persional();
				persional.setId(resultSet.getInt("id"));
				persional.setCity(resultSet.getString("city"));
				persional.setCommunity(resultSet.getString("community"));
				persional.setStreet(resultSet.getString("street"));
				persional.setName(resultSet.getString("name"));
				persional.setSex(resultSet.getString("sex"));
				persional.setCard_no(resultSet.getString("card_no"));
				persional.setRegister_date(resultSet.getString("register_date"));
				list.add(persional);
			} 
			BaseDao.colse(connection, preparedStatement, resultSet);
			return list;
	}
	
	public int add(Persional persional) throws SQLException{
		 connection=BaseDao.getConnection();
		String sql="insert into tb_population values(0,?,?,?,?,?,?,?)";
		try{
			 preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, persional.getCity());
			preparedStatement.setString(2, persional.getCommunity());
			preparedStatement.setString(3, persional.getStreet());
			preparedStatement.setString(4, persional.getName());
			preparedStatement.setString(5, persional.getSex());
			preparedStatement.setString(6, persional.getCard_no());
			preparedStatement.setString(7, persional.getRegister_date());
//			preparedStatement.setDate(7, new Date(persional.getRegister_date().getTime()));
//			System.out.println(new Date(persional.getRegister_date().getTime()));
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
		}finally{
			BaseDao.colse(connection, preparedStatement, resultSet);
		}
		return 0;
	}
}
