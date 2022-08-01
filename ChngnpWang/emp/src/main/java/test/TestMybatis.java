package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.empMapper;
import model.emp;

public class TestMybatis {
	
	public static void main(String[] args) throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("config/mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		empMapper mapper = sqlSession.getMapper(empMapper.class);
		
		List<emp> empList = mapper.queryAll();
		
		System.out.println(empList);
		sqlSession.close();
		
	}

}
