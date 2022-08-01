package com.good.model;

public class Goods {
	private int goodId;
	private String goodName;
	private int num;
	private double price;
	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Goods(int goodId, String goodName, int num, double price) {
		super();
		this.goodId = goodId;
		this.goodName = goodName;
		this.num = num;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Goods [goodId=" + goodId + ", goodName=" + goodName + ", num=" + num + ", price=" + price + "]";
	}
	public int getGoodId() {
		return goodId;
	}
	public void setGoodId(int goodId) {
		this.goodId = goodId;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	
}
