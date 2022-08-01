package entity;
/*
 * 学生实体类
 * */
public class teacher {
	private int id; //教师编号
	private String name; //教师姓名
	private String sex; //教师性别
	private int age; //教师年龄
	private String position; //职务
	
	public teacher(int id, String name, String sex, int age, String position) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.position = position;
	}
	
	public teacher() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	

}
