package model;

public class emp {
	private int empId; //员工编号 int 主键， 自动递增
	private String empName; //员工姓名 varchar 20 不能为空
	private String sex; //员工性别 varchar 4 不能为空
	private Float salary; //员工工资 decimal(10,2) 不能为空
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "emp [empId=" + empId + ", empName=" + empName + ", sex=" + sex + ", salary=" + salary + "]";
	}


}
