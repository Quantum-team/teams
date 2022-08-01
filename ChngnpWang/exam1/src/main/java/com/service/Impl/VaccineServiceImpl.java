package com.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mapper.VaccineMapper;
import com.pojo.vaccine;
import com.service.VaccineService;

@Service
public class VaccineServiceImpl implements VaccineService {
	@Resource
	private VaccineMapper vaccineMapper;
	//全表查询
	@Override
	public List<vaccine> selectAll() {
		List<vaccine> list = vaccineMapper.queryAll();
		return list;
	}

}
