package com.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.book.model.Book;
import com.book.utilis.BaseDao;

public class BookDao {
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	
	public List<Book> selectAll() throws SQLException{
		connection=BaseDao.getConnection();
		String sql="select * from tb_book";
		preparedStatement=connection.prepareStatement(sql);
		resultSet=preparedStatement.executeQuery();
		List<Book> list=new ArrayList<Book>();
		if (resultSet!=null) {
			while (resultSet.next()) {
				Book book2=new Book();
				book2.setId(resultSet.getInt("id"));
				book2.setBookName(resultSet.getString("bookName"));
				book2.setAuthor(resultSet.getString("author"));
				book2.setSex(resultSet.getString("sex"));
				book2.setPrice(resultSet.getFloat("price"));
				book2.setBookDesc(resultSet.getString("bookDesc"));
				list.add(book2);
			}
			return list;
		}else {
			BaseDao.close(connection, preparedStatement, resultSet);
		}
		return null;
	}
	
	public int delete(int id) throws SQLException{
		connection=BaseDao.getConnection();
		String sql="delete from tb_book where id=?";
		try{
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(0, id);
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			BaseDao.close(connection, preparedStatement, resultSet);
		}
		return 0;
	}
}
