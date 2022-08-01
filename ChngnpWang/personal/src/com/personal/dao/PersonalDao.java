package com.personal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.personal.model.Persomal;
import com.personal.utilis.BaseDao;


public class PersonalDao {
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	
	//全表查询
/*	public List<Personal> seletteAll() throws SQLException{
 *      //1、建立连接
		connection=BaseDao.getConnect();
		//2、书写sql语句
		String sql="select * from tb_employee";
		//3、创建命令管理器
		preparedStatement=connection.prepareStatement(sql);
		//4、执行sql语句
		resultSet=preparedStatement.executeQuery();
		//5、处理结果集
		List<Personal> list=new ArrayList<Personal>();
		if (resultSet!=null) {
			while (resultSet.next()) {
				Personal personal=new Personal();
				personal.setEmpId(resultSet.getInt("EmpId"));
				personal.setEmpName(resultSet.getString("EmpName"));
				personal.setEmpSex(resultSet.getString("empSex"));
				personal.setEmpPhone(resultSet.getString("empPhone"));
				personal.setEmpAddress(resultSet.getString("empAddress"));
				//将personal对象存储到list里面
				list.add(personal);
			}
			return list;
		}else {
		//6、关闭连接
			BaseDao.close(connection, preparedStatement, resultSet);
		}
		return null;
	}*/

	//查询
	public List<Persomal> select(String name) throws SQLException{
		//1、建立连接
		connection=BaseDao.getConnect();
		//2、书写sql语句
		String sql=null;
		if (name!=null) {
			sql = "select * from tb_employee where empName like '%"+name+"%'";
		}else {
			sql = "select * from tb_employee";
		}
//		System.out.println(sql);
		//3、创建命令管理器
		preparedStatement=connection.prepareStatement(sql);
		//4、执行sql语句
		resultSet=preparedStatement.executeQuery();
		//5、处理结果集
		List<Persomal> list=new ArrayList<Persomal>();
			while (resultSet.next()) {
				Persomal personal=new Persomal();
				personal.setEmpId(resultSet.getInt("EmpId"));
				personal.setEmpName(resultSet.getString("EmpName"));
				personal.setEmpSex(resultSet.getString("empSex"));
				personal.setEmpPhone(resultSet.getString("empPhone"));
				personal.setEmpAddress(resultSet.getString("empAddress"));
				//将personal对象存储到list里面
				list.add(personal);
			}
			//6、关闭连接
			BaseDao.close(connection, preparedStatement, resultSet);
			return list;
	}
	
	//删除
	public int deletePersonal(int id) throws SQLException{
		//1、建立连接
		connection=BaseDao.getConnect();
		//2、书写sql语句
		String sql="delete from tb_employee where empId=?";
		try{
			//3、创建命令管理器
			preparedStatement=connection.prepareStatement(sql);
			//4、给占位符赋值
			preparedStatement.setInt(1, id);
			//5、执行sql语句
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			//6、关闭连接
			BaseDao.close(connection, preparedStatement, resultSet);
		}
		return 0;
	}

	//添加
	public int addPersonal(Persomal personal) throws SQLException{
        //1、建立连接
		connection=BaseDao.getConnect();
		//2、书写sql语句
		String sql="insert into tb_employee values(0,?,?,?,?)";
		try{
		//3、创建命令管理器
			preparedStatement=connection.prepareStatement(sql);
			//4、给占位符赋值
			preparedStatement.setString(1, personal.getEmpName());
			preparedStatement.setString(2, personal.getEmpSex());
			preparedStatement.setString(3, personal.getEmpPhone());
			preparedStatement.setString(4, personal.getEmpAddress());
			//5、执行sql语句
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
		//6、关闭连接
			BaseDao.close(connection, preparedStatement, resultSet);
		}
		return 0;
	}
	
//	public List<Persomal> selete(String name) throws SQLException{
//		connection=BaseDao.getConnect();
//		StringBuilder sb_sql = new StringBuilder("select empId, empName, empSex, empPhone, empAddress from tb_employee");
//		if (name!=null&&!name.trim().isEmpty()) {
//			sb_sql.append(" where empName like '%"+name+"%'");
//		}
//		String sql = sb_sql.toString();
//		System.out.println(sql);
//		preparedStatement=connection.prepareStatement(sql);
//		resultSet=preparedStatement.executeQuery();
//		List<Persomal> list=new ArrayList<Persomal>();
//		if (resultSet!=null) {
//			System.out.println(resultSet.next());
//			while (resultSet.next()) {
//				Persomal personal=new Persomal();
//				personal.setEmpId(resultSet.getInt("empId"));
//				personal.setEmpName(resultSet.getString("empName"));
//				personal.setEmpSex(resultSet.getString("empSex"));
//				personal.setEmpPhone(resultSet.getString("empPhone"));
//				personal.setEmpAddress(resultSet.getString("empAddress"));
//				//将personal对象存储到list里面
//				list.add(personal);
//			}
//	}else {
//		BaseDao.close(connection, preparedStatement, resultSet);
//	}
//		return list;
//	}
}
