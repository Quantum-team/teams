package com.liu.pojo;

import java.sql.Date;

/**
 * 
 * @author 刘老师
 * 
 * - 源码请所有同学合理使用，禁止非学习用途。
 * - 参照源码多想多练多Debug，杜绝无脑Copy！
 * - 有问题找学委统一汇总，课堂答疑，也可到办公室问我。
 * - 小红书|微信视频号 @老刘编程 三连到位，禁止下次一定！
 *
 */
public class Source {

	private Integer id;
	private String name;
	private String type;
	private Date buyDate;
	private String price;
	private String imageUrl;
	private String status;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
