package com.order.dao;

public class Test {
	public static void main(String[] args) {
		OrderDao orderDao=new OrderDao();
		try{
			orderDao.seleteAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
