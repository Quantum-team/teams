package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.emp;

public interface empMapper {
	//查全部
	List<emp> queryAll();
	
	//根据name查询
	List<emp> queryByName(@Param("name") String name);
	
	//根据id删除
	
	int delById(@Param("id")int id);
	

}
