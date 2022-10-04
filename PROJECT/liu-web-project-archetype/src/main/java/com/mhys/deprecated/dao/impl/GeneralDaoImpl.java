package com.mhys.deprecated.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mhys.deprecated.dao.IGeneralDao;
import com.mhys.util.Util;

public class GeneralDaoImpl<T> implements IGeneralDao<T> {

	private String tableName;

	@Override
	public T queryById(Integer id) {
		return null;
	}

	@Override
	public T queryByCondition(String condition) {
		String sql = "select * from " + tableName + " where " + condition;
		
		try {
			ResultSet result = Util.prepareStatement().executeQuery(sql);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
