package com.personal.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.personal.Utili.JDBCutili;
import com.personal.moule.Per;
import com.personal.moule.Personal;

public class BaseDao {
	Connection con=null;
	PreparedStatement smt=null;
	ResultSet rs=null;
	
	public List<Per> selectAll(String city) throws SQLException{
		con=JDBCutili.getConn();
		String sql=null;
		if (city!=null) {
			sql="select * from population where city like '%"+city+"%'";
		}else {
			sql="select * from population";
		}
		smt=con.prepareStatement(sql);
		rs=smt.executeQuery();
		List<Per> list=new ArrayList<Per>();
		while (rs.next()) {
			Per personal=new Per();
			personal.setId(rs.getInt("id"));
			personal.setCity(rs.getString("city"));
			personal.setCommunity(rs.getString("community"));
			personal.setStreet(rs.getString("street"));
			personal.setName(rs.getString("name"));
			personal.setSex(rs.getString("sex"));
			personal.setCard_no(rs.getString("card_no"));
			personal.setRegister_data(rs.getDate("register_data"));
			list.add(personal);
		}
		JDBCutili.close(con, smt, rs);
		return list;
	}
	
	public int add(Per personal) throws SQLException{
		con=JDBCutili.getConn();
		String sql="insert into population values(0,?,?,?,?,?,?,?)";
		smt=con.prepareStatement(sql);
		try{
			 smt=con.prepareStatement(sql);
			smt.setString(1, personal.getCity());
			smt.setString(2, personal.getCommunity());
			smt.setString(3, personal.getStreet());
			smt.setString(4, personal.getName());
			smt.setString(5, personal.getSex());
			smt.setString(6, personal.getCard_no());
			smt.setDate(7, new Date(personal.getRegister_data().getTime()));
		}finally {
			JDBCutili.close(con, smt, rs);
		}
		return 0;
	}
}
