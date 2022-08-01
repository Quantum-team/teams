package com.movies.model;

import java.util.Date;

public class Movies {
	private int id;
	private String name;
	private int duration;
	private String area;
	private Date moviedate;
	private double price;
	public Movies() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Movies(int id, String name, int duration, String area, Date moviedate, double price) {
		super();
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.area = area;
		this.moviedate = moviedate;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Movies [id=" + id + ", name=" + name + ", duration=" + duration + ", area=" + area + ", moviedate="
				+ moviedate + ", price=" + price + "]";
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
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Date getMoviedate() {
		return moviedate;
	}
	public void setMoviedate(Date moviedate) {
		this.moviedate = moviedate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	
}
