package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pojo.vaccine;


public interface VaccineMapper {
	//查全部
	List<vaccine> queryAll();
}
