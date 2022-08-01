package mapper;

import org.apache.ibatis.annotations.*;
import pojo.User;

import java.util.List;

public interface UserMapper {
    @Results(id = "userMap",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "userName",column = "user_name"),
            @Result(property = "password",column = "password"),
            @Result(property = "name",column = "name"),
            @Result(property = "age",column = "age"),
            @Result(property = "email",column = "email"),
    })
    //查询全部
    @Select("select * from tb_user")
    List<User> queryAll();
    //删除
    @Delete("delete from tb_user where id=#{id}")
    int delById(int id);
    //新增
    @Insert("insert into tb_user values(#{id},#{userName},#{password},#{name},#{age},#{email})")
    int addUser(User user);
}
