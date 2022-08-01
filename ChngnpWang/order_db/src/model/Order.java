package model;

import java.util.Date;

public class Order {
	private int id;
	private int odd;
	private String name;
	private String order;
	private int quantity;
	private int telephone;
	private String address;
	private String note;
	private Date date;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int id, int odd, String name, String order, int quantity, int telephone, String address, String note,
			Date date) {
		super();
		this.id = id;
		this.odd = odd;
		this.name = name;
		this.order = order;
		this.quantity = quantity;
		this.telephone = telephone;
		this.address = address;
		this.note = note;
		this.date = date;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", odd=" + odd + ", name=" + name + ", order=" + order + ", quantity=" + quantity
				+ ", telephone=" + telephone + ", address=" + address + ", note=" + note + ", date=" + date + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOdd() {
		return odd;
	}
	public void setOdd(int odd) {
		this.odd = odd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	
}
