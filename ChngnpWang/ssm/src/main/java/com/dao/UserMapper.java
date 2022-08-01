package com.dao;

import com.pojo.user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName:UserMapper
 * PackageName:com.dao
 * Description:
 *
 * @date:2022/5/11 12:45
 * @author: YuanCoding
 */
@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<user> getAll();
}
