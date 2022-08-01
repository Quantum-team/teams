package com.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pojo.user;
import org.apache.ibatis.annotations.Mapper;
/**
 * ClassName:UserMaper
 * PackageName:com.dao
 * Description:
 *
 * @date:2022/5/11 13:53
 * @author: YuanCoding
 */
@Mapper
@TableName(value = "user")
public interface UserMaper extends BaseMapper<user>{
}
