package model;

import java.util.Date;

public class source {
	
	private int id; //编号 int 主键，自增，增量为 1
	private String name;//名称 varchar 50 不能为空
	private String type; //类型 varchar 20 不能为空
	private String buyDate; //采购时间 date 不能为空
	private int price; //价格 int 不能为空
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public source(int id, String name, String type, String buyDate, int price) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.buyDate = buyDate;
		this.price = price;
	}
	public source() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "source [id=" + id + ", name=" + name + ", type=" + type + ", buyDate=" + buyDate + ", price=" + price
				+ "]";
	}

}
