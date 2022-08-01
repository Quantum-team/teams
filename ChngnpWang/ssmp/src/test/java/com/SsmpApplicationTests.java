package com;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dao.UserMaper;
import com.pojo.user;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import sun.security.util.AuthResources_it;

@SpringBootTest
@MapperScan("com.dao.*")
class SsmpApplicationTests {
    @Autowired
    private UserMaper userMaper;

    //插入数据
    @Test
    void addUser(){
        //模拟对象
        user u = new user();
//        u.setId(0);
        u.setName("李四");
        u.setAge(18);
        int i = userMaper.insert(u);
        if (i>0){
            System.out.println("成功");
        }else{
            System.out.println("失败");
        }
    }
    //删除
    @Test
    void delUser() {
        int i = userMaper.deleteById(2);
        System.out.println("删除条数"+i);
    }
    //查询所有
    @Test
    void getAll(){
        System.out.println(userMaper.selectList(null));
    }
    //根据id查询
    @Test
    void getOne(){
        System.out.println(userMaper.selectById(1));
    }
    //修改
    @Test
    void updateUser(){
        //模拟对象
        user u = new user();
        u.setName("李四");
        u.setAge(19);

        //设置id
        u.setId(2);
        int i = userMaper.updateById(u);
        if (i>0){
            System.out.println("成功");
        }else{
            System.out.println("失败");
        }
    }
    //分页查询
    @Test
    void getPage(){
        IPage page = new Page(1,5);
//        IPage page = new Page(2,5);
        System.out.println(userMaper.selectPage(page,null));

        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.getRecords());

    }
    //根据条件查询
    @Test
    void getBy() {
        QueryWrapper<user> qw = new QueryWrapper<>();
//        模糊查询
        qw.like("name", "张");


        userMaper.selectList(qw);
    }
//    语法级别检查
    @Test
    void getBy1(){
        LambdaQueryWrapper<user> lqw = new LambdaQueryWrapper<>();
        String name = "张";
//        String name = null;
        int id = 1;
//        lqw.like(user::getId,name);
        lqw.like(user::getId,id);
        userMaper.selectList(lqw);

    }

}
