package com.personal.model;

//实体类
public class Persomal {
	private int empId;
	private String empName;
	private String empSex;
	private String empPhone;;
	private String empAddress;
	public Persomal() {
		super();
	}
	public Persomal(int empId, String empName, String empSex, String empPhone, String empAddress) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empSex = empSex;
		this.empPhone = empPhone;
		this.empAddress = empAddress;
	}
	@Override
	public String toString() {
		return "Personal [empId=" + empId + ", empName=" + empName + ", empSex=" + empSex + ", empPhone=" + empPhone
				+ ", empAddress=" + empAddress + "]";
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpSex() {
		return empSex;
	}
	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}
	public String getEmpPhone() {
		return empPhone;
	}
	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	
	
}
