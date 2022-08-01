package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.dao.Studao;
import com.po.Student;
@Service
public class StuseviceImpl implements Stuservice {
@Resource
private Studao studao;
//全表查询
	@Override
	public List selectAllStu() {
		List students = studao.selectAllStu();
		return students;
	}
	
	//根据id查询
	@Override
	public List<Student> SelStudentById(String id) {
		List<Student> students = studao.SelStudentById(id);
		return students;
	}

	//根据id删除
	@Override
	public int DelStuId(String id) {
		int a = studao.DelStuId(id);
		return a;
	}

	//新增
	@Override
	public int insertStudent(Student student) {
		int i = studao.insertStudent(student);
		return i;
	}

	//修改
	@Override
	public int updateStuid(Student student) {
		int i  = studao.updateStuid(student);
		return i;
	}

}
