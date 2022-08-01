package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.model.Pro;
import com.project.utilis.JDBCUtils;

public class BaseDao {
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	public List<Pro> seleteAll() throws SQLException{
		connection=JDBCUtils.getConnection();
		String sql="select * from projec_db";
		preparedStatement=connection.prepareStatement(sql);
		resultSet=preparedStatement.executeQuery();
		List<Pro> list=new ArrayList<Pro>();
		if (resultSet!=null) {
			while (resultSet.next()) {
			Pro pro=new Pro();
			pro.setPrj_id(resultSet.getInt("prj_id"));
			pro.setPrj_name(resultSet.getString("prj_name"));
			pro.setPrj_cycle(resultSet.getInt("prj_cycle"));
			pro.setPrj_count(resultSet.getInt("prj_count"));
			pro.setPrj_manager(resultSet.getString("prj_manager"));
			list.add(pro);
			}
			return list;
		}else{
			JDBCUtils.close(connection, preparedStatement, resultSet);
		}
		return null;
	}
	
	public int addProject(Pro pro) throws SQLException{
		connection=JDBCUtils.getConnection();
		String sql="insert into tb_prj values(0,?,?,?,?)";
		try{
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, pro.getPrj_name());
			preparedStatement.setInt(2, pro.getPrj_cycle());
			preparedStatement.setInt(3, pro.getPrj_count());
			preparedStatement.setString(4, pro.getPrj_manager());
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(connection, preparedStatement, resultSet);
		}
		return 0;
	}
	
	public int deleteProject(int id) throws SQLException{
		connection=JDBCUtils.getConnection();
		try{
			String sql="delete from tb_prj where prj_id=?";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(connection, preparedStatement, resultSet);
		}
		return 0;
	}
}