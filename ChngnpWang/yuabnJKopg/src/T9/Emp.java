package T9;

public class Emp {
	private String no;//员工编号
	private String name;//姓名
	private String birthday;//生日
	private String address;//
	private String post;//职务
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String toString(){
		return this.no+"-"+this.name+"-"+this.birthday+"-"
			    +this.getAddress()+"-"+this.post;
	}

}
