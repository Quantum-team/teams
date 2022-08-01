package com.good.dao;

import java.sql.SQLException;
import java.util.List;

import com.good.model.Goods;

public class Test {
	public static void main(String[] args) throws SQLException {
		GoodsDao goodsDao=new GoodsDao();
		List<Goods> list=goodsDao.select(null);
		if (list != null) {
			System.out.println("fhsjkfhs");
		}else {
			System.out.println("ahfgH");
		}
		for(Goods goods:list){
			System.out.println(goods);
		}
	}
}
