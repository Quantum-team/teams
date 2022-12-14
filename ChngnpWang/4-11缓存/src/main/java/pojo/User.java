package pojo;

import java.util.Arrays;

public class User {
	

	private Long id;
	private String userName;
	private String userPassword;
	private String userEmail;
	private String userInfo;
	private byte[] headImg;
	private String createTime;
	private int deptId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}
	public byte[] getHeadImg() {
		return headImg;
	}
	public void setHeadImg(byte[] headImg) {
		this.headImg = headImg;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userPassword=" + userPassword + ", userEmail="
				+ userEmail + ", userInfo=" + userInfo + ", headImg=" + Arrays.toString(headImg) + ", createTime="
				+ createTime + ", deptId=" + deptId + "]";
	}
	
	
	
	
	

}
