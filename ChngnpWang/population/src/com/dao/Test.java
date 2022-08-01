package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.model.Persional;

public class Test {
public static void main(String[] args) throws SQLException {
	Dao   dao=new Dao();
	List<Persional> list=dao.select("昆明");
	for(Persional persional:list){
		System.out.println(persional);
	}
}
}
