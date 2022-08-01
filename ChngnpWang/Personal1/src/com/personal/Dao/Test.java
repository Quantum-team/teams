package com.personal.Dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.personal.moule.Per;
import com.personal.moule.Personal;

public class Test {
	public static void main(String[] args) throws SQLException {
		BaseDao dao=new BaseDao();
		List<Per> list=dao.selectAll(null);
		for (Per per:list) {
			System.out.println(per);
		}
	}
}
