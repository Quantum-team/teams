package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.EmployeeMapper;
import model.Employee;

public class DaoTest {
	
	
	public static void main(String[] args) throws IOException {
		//读配置文件
		InputStream resourceAsStream = Resources.getResourceAsStream("conf/mybatis-config.xml");
		//创建工厂会话
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		//打开会话
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//jiazai  mapper
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		//接收返回值
		
		/*List<Employee> findAll = mapper.findAll();
		
		System.out.println(findAll);
		*/
		/*
		Employee findById = mapper.findById(2);
		
		System.out.println(findById);*/
		
//		Employee employee = new Employee();
//		employee.setEmpId(1);
//		employee.setEmpName("张三");
//		employee.setEmpSex("男");
//		employee.setEmpPhone("110");
//		employee.setEmpAddress("中国");
//		
//		int i = mapper.addEmp(employee);
//		
//		if (i>0) {
//			System.out.println("tianjiachneg");
//		}
		
		Employee delete=mapper.deleteID(2);
		System.out.println(delete);
	}
}
