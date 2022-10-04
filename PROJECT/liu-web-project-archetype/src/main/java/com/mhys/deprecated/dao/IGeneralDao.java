package com.mhys.deprecated.dao;

public interface IGeneralDao<T> {
	
	public T queryById(Integer id);
	
	public T queryByCondition(String condition);
}
