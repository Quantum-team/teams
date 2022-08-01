package com.order.model;

public class Order {
	private int id;
	private String name;
	private double price;
	private String orderId;
	private String descinfo;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int id, String name, double price, String orderId, String descinfo) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.orderId = orderId;
		this.descinfo = descinfo;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + ", price=" + price + ", orderId=" + orderId + ", descinfo="
				+ descinfo + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getDescinfo() {
		return descinfo;
	}
	public void setDescinfo(String descinfo) {
		this.descinfo = descinfo;
	}

	
}
