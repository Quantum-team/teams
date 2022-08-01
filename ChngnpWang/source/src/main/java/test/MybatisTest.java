package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.sourceMapper;
import model.source;

public class MybatisTest {
	private static SqlSessionFactory sqlSessionFactory ;
	private static SqlSession sqlSession;
	public static void main(String[] args) {
			
			try {
				InputStream resourceAsStream = Resources.getResourceAsStream("config/mybatis-config.xml");
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
				 sqlSession = sqlSessionFactory.openSession();
				 
				 sourceMapper mapper = sqlSession.getMapper(sourceMapper.class);
				 
				 List<source> sourceList = mapper.findAll();
				 
				 System.out.println(sourceList);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
