package dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.*;

public interface sourceMapper {
	//查全部
	List<source> findAll();
	//根据id删除信息
	int delById(@Param("id") int id);
	//根据名称模糊查询
	List<source> queryByName(@Param("name")String name);
	//添加信息
	int addSource(source source);
}
