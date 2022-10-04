package com.liu.cases2;

/**
 * Java bean
 */
public class Animal {

	// private attributes
	private Integer id;
	private String name;
	private Double age;
	private Boolean eatable = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAge() {
		return age;
	}

	public void setAge(Double age) {
		this.age = age;
	}

	public Boolean getEatable() {
		return eatable;
	}

	public void setEatable(Boolean eatable) {
		this.eatable = eatable;
	}
}
