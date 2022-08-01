package com.personal.moule;

public class Personal {
	private int id;
	private String city;
	private String community;
	private String street;
	private String name;
	private String sex;
	private String card_no;
	private String register_data;
	public Personal() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Personal(int id, String city, String community, String street, String name, String sex, String card_no,
			String register_data) {
		super();
		this.id = id;
		this.city = city;
		this.community = community;
		this.street = street;
		this.name = name;
		this.sex = sex;
		this.card_no = card_no;
		this.register_data = register_data;
	}
	@Override
	public String toString() {
		return "Personal [id=" + id + ", city=" + city + ", community=" + community + ", street=" + street + ", name="
				+ name + ", sex=" + sex + ", card_no=" + card_no + ", register_data=" + register_data + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public String getRegister_data() {
		return register_data;
	}
	public void setRegister_data(String register_data) {
		this.register_data = register_data;
	}

	
}
