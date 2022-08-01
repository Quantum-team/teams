package com.library.dao;

import java.sql.SQLException;

import com.library.model.Book;

public class test {

	public static void main(String[] args) {
BookDao bookDao=new BookDao();

try {
	bookDao.addBook(new Book(1, "1", "1", "1", 2, "1"));
} catch (SQLException e) {
	e.printStackTrace();
}
	}

}
