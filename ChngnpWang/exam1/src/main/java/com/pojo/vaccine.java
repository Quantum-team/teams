package com.pojo;

public class vaccine {
	private int id; //编号 int 主键，自增，增量为 1
    private String name; //姓名 varchar 50 不能为空
    private String sex; //性别 varchar 10 不能为空
    private String idNo; //身份证号 varchar 50 不能为空
    private String address; //所在地址 varchar 200 不能为空
    private int company; //疫苗生产企业 int 1：北京科兴 2：武汉生物 3：北京生物 0：//其他
    private String inoculationTime; //接种时间 varchar 100 添加时间默认当前时间
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCompany() {
		return company;
	}
	public void setCompany(int company) {
		this.company = company;
	}
	public String getInoculationTime() {
		return inoculationTime;
	}
	public void setInoculationTime(String inoculationTime) {
		this.inoculationTime = inoculationTime;
	}
	@Override
	public String toString() {
		return "vaccine [id=" + id + ", name=" + name + ", sex=" + sex + ", idNo=" + idNo + ", address=" + address
				+ ", company=" + company + ", inoculationTime=" + inoculationTime + "]";
	}
	
    
}
