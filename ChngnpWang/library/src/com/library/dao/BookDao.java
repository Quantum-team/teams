package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.model.Book;
import com.library.utilis.BaseDao;

public class BookDao {
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	//全表查询
	public List<Book> selectAll() throws SQLException{
		//建立连接
		connection=BaseDao.getConnection();
		String sql="select * from tb_book";
		preparedStatement=connection.prepareStatement(sql);
		resultSet=preparedStatement.executeQuery();
		List<Book> list=new ArrayList<Book>();
		if (resultSet!=null) {
			while (resultSet.next()) {
				Book book=new Book();
				book.setBookId(resultSet.getInt("BookId"));
				book.setBookName(resultSet.getString("bookName"));
				book.setBookAuthor(resultSet.getString("bookAuthor"));
				book.setBookPublish(resultSet.getString("bookPublish"));
				book.setBookPrice(resultSet.getDouble("bookPrice"));
				book.setBookType(resultSet.getString("bookType"));
				list.add(book);
			}
			return list;
		} else {
			BaseDao.close(connection, preparedStatement, resultSet);
		}
		return null;
	}
	
	//删除图书
	public int deleteBook(int id) throws SQLException{
		connection=BaseDao.getConnection();
		String sql="delete from tb_book where bookId=?";
		try{
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
	
	//添加图书休息
	public int addBook(Book book) throws SQLException{
		connection=BaseDao.getConnection();
		String sql="insert into tb_book values(0,?,?,?,?,?)";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, book.getBookName());
			preparedStatement.setString(2, book.getBookAuthor());
			preparedStatement.setString(3, book.getBookPublish());
			preparedStatement.setDouble(4, book.getBookPrice());
			preparedStatement.setString(5, book.getBookType());
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			BaseDao.close(connection, preparedStatement, resultSet);
		}
		return 0;
	}
}
