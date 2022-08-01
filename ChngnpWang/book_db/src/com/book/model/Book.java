package com.book.model;

public class Book {
	private int id;
	private String bookName;
	private String author;
	private String sex;
	private double price;
	private String bookDesc;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(int id, String bookName, String author, String sex, double price, String bookDesc) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.author = author;
		this.sex = sex;
		this.price = price;
		this.bookDesc = bookDesc;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", author=" + author + ", sex=" + sex + ", price=" + price
				+ ", bookDesc=" + bookDesc + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getBookDesc() {
		return bookDesc;
	}
	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}

	
}
