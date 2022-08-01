import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import pojo.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class mpTest {
    private InputStream resourceAsStream;
    private SqlSession sqlSession;
    private String resource = "mybatis-config.xml";

    //查询全部
    @Test
    public void test1(){
        try {
             resourceAsStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //打开会话
        sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> users = userMapper.queryAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
    //删除数据
    @Test
    public void test2(){
        try {
            resourceAsStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //打开会话
        sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        int result = userMapper.delById(5);
        if (result>0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
        sqlSession.commit();
        sqlSession.close();
    }
    //插入
    //删除数据
    @Test
    public void test3(){
        try {
            resourceAsStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //打开会话
        sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //组装对象
        User user = new User();
        user.setId(5L);
        user.setUserName("sunqi");
        user.setPassword("123456");
        user.setName("孙七");
        user.setAge(18);
        user.setEmail("test5@baidu.cn");
        int result = userMapper.addUser(user);

        if (result>0) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }
        sqlSession.commit();
        sqlSession.close();
    }


}
