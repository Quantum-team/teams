package com.liu.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liu.dao.IUserDao;
import com.liu.dao.IPersonDao;
import com.liu.pojo.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.liu.controller.EntranceController;
import com.liu.controller.ListController;
import com.liu.pojo.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author 刘老师
 *
 * - 源码请所有同学合理使用，禁止非学习用途。
 * - 参照源码多想多练多Debug，杜绝无脑Copy！
 * - 有问题找学委统一汇总，课堂答疑，也可到办公室问我。
 * - 小红书|微信视频号 @老刘编程 三连到位，禁止下次一定！
 *
 * - Spring 单元测试类，根据任意层的任意单元模块进行测试，无需启动Web应用
 * - 执行asssert/断言进行结果判定，断言必须有良好的执行语义
 *
 */

// 使用注解方式注册配置文件
@Configuration
// 开启组件扫描，注意包路径
@ComponentScan(basePackages = "com.liu")
// 指定基础资源配置文件
@PropertySource(value = { "classpath:application.properties" })
// 指定本类为 JUnit 单元测试类
@RunWith(SpringRunner.class)
// 指定本类为 Spring 单元测试类，集合 Spring 测试组件
@SpringBootTest(classes = { TestCases.class })
class TestCases {

	@Autowired
	private EntranceController entranceController;
	
	@Autowired
	private ListController listController;

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IPersonDao personDao;

	// 注入 session
	@Autowired
	private HttpSession session;

	// Json 对象映射
	private static ObjectMapper mapper = new ObjectMapper();

	// 模拟登陆用户，空用户，使用时手动注入属性
	private static User fakeUser = new User();

	// 模拟登陆用户，已注入基本属性
	private static User fakeUserWithAccount = new User();

	// 静态加载块
	static {

		// 注入模拟用户基本属性
		fakeUserWithAccount.setUsername("matthew");
		fakeUserWithAccount.setPassword("123");
	}
	
	/**
	 * 辅助理解 assert 表达式的方法，断言结果为 true
	 * @param assertResult
	 * @return
	 */
	private static boolean isTrue(boolean assertResult) {

		return assertResult ? true : false;
	}


	/**
	 * 辅助理解 assert 表达式的方法，断言结果为 false
	 * @param assertResult
	 * @return
	 */
	private static boolean isFalse(boolean assertResult) {

		return !isTrue(assertResult);
	}


	@Test
	void test_1() {

		List<User> list_1 = userDao.queryList();
		list_1.forEach(System.out::println);

	}

	@Test
	void test_2() throws JsonProcessingException {

		// TODO 执行断言，登陆成功结果
		fakeUser.setUsername("matthew");
		fakeUser.setPassword("123");
		// {"result" : true }
		// {"result" : false }

		String jsonResult = entranceController.doLogin(fakeUser);
		JsonNode node = mapper.readTree(jsonResult);
		assert isTrue(node.path("result").asBoolean());


		// TODO ====================================================


		// TODO 执行断言，登陆失败结果
		fakeUser.setUsername("test_false");
		fakeUser.setPassword("11");
		// {"result" : false }

		String jsonResult_ = entranceController.doLogin(fakeUser);
		JsonNode node_ = mapper.readTree(jsonResult_);
		assert isFalse(node_.path("result").asBoolean());

	}

	@Test
	void test_3() {

		List<Person> list_1 = personDao.queryList();
		list_1.forEach(System.out::println);

	}
	
	@Test
	void test_4() throws JsonProcessingException {
		
		// 测试分页，第1页，5条数据
		String json = listController.jsonListPersonPaging(0, 5);
		System.out.println(json);
		
//		 {
//			 "content":
//				 [{"id":1,"status":"0","timestamp":1649058899000,"job":"Web Developer","realName":"Nabiha Armstrong","email":"nabiha.armstrong@example.com","location":"Cluj Napoca, RO","avatar":null},
//				  {"id":2,"status":"0","timestamp":1649058931000,"job":"Product Designer","realName":"Dan Holding","email":"dan.holding@example.com","location":"New York, USA","avatar":null},
//				  {"id":3,"status":"0","timestamp":1649058971000,"job":"Will Smith","realName":"Sales Manager","email":"will.smith@example.com","location":"Los Angeles, USA","avatar":null},
//				  {"id":4,"status":"0","timestamp":null,"job":"j4","realName":"r4","email":"e4","location":"l4","avatar":""},
//				  {"id":5,"status":null,"timestamp":null,"job":"j5","realName":"r5","email":"e5","location":"l5","avatar":null}
//				 ],
//				 "pageable":{
//				 	"sort":{
//				 		"empty":true,
//				 		"sorted":false,
//				 		"unsorted":true
//				 		},
//				 	"offset":0,
//				 	"pageNumber":0,
//				 	"pageSize":5,
//				 	"paged":true,
//				 	"unpaged":false
//				 	},
//				 "last":false,
//				 "totalPages":2,
//				 "totalElements":9,
//				 "numberOfElements":5,
//				 "sort":{
//				 		"empty":true,
//				 		"sorted":false,
//				 		"unsorted":true
//				 		},
//				 "size":5,
//				 "number":0,
//				 "first":true,
//				 "empty":false
//		 }
		// 最终得到 JSON 格式数据样例带数据信息和分页信息供前端控件使用
	
		
	}
	

}
