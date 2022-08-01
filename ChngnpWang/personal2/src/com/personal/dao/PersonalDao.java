package com.personal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.personal.model.Personal;
import com.personal.utils.BaseDao;

public class PersonalDao {
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	
	//模糊查询
	public List<Personal> select(String name) throws SQLException{
		//连接数据库
		connection=BaseDao.getConnection();
		//书写sql
		String sql=null;
		if (name!=null) {
			sql="select * from employee where empName like '%"+name+"%'";
		}else {
			sql="select * from employee";
		}
		//创建命令管理器
		preparedStatement=connection.prepareStatement(sql);
		//执行sql
		resultSet=preparedStatement.executeQuery();
		//处理结果集
		List<Personal> list=new ArrayList<Personal>();
		while (resultSet.next()) {
			Personal personal=new Personal();
			personal.setEmpId(resultSet.getInt("empId"));
			personal.setEmpName(resultSet.getString("empName"));
			personal.setEmpSex(resultSet.getString("empSex"));
			personal.setEmpPhone(resultSet.getString("empPhone"));
			personal.setEmpAddress(resultSet.getString("empAddress"));
			list.add(personal);
		}
		//关闭数据库
		BaseDao.colse(connection, preparedStatement, resultSet);
		return list;
	}
	
	
	public int delete(int id) throws SQLException {
		//连接数据库
		connection=BaseDao.getConnection();
		//书写sql
		String sql="delete from employee where empId=?";
		try {
			//创建命令管理器
		preparedStatement=connection.prepareStatement(sql);
		//给？赋值
		preparedStatement.setInt(1, id);
		//执行sql
		return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			//关闭数据库
			BaseDao.colse(connection, preparedStatement, resultSet);
		}
		return 0;
	}
}
