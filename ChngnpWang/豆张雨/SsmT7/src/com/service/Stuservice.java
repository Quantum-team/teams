package com.service;

import java.util.List;

import com.po.Student;


public interface Stuservice {
//查询全部
List selectAllStu();

//根据id查询
public List<Student> SelStudentById(String id);

//根据id删除
public int DelStuId(String id);

//新增
public int insertStudent(Student student);

//修改
public int updateStuid(Student student);
}
