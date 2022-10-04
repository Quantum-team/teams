package com.liu.cases;

import java.util.HashMap;
import java.util.Map;

public class T {

	public static void main(String[] args) { 
		
		Map<Integer, Student> map_1 = new HashMap<>();

		Student student_1 = new Student();
		student_1.setId(1);
		student_1.setAge(21);
		student_1.setName("weige");
		map_1.put(student_1.getId(), student_1);

		Student student_2 = new Student();
		student_2.setId(2);
		student_2.setAge(22);
		student_2.setName("zs");
		map_1.put(student_2.getId(), student_2);

		Student student_3 = new Student();
		student_3.setId(3);
		student_3.setAge(23);
		student_3.setName("ls");
		map_1.put(student_3.getId(), student_3);
		
		/**
		 * key	value
		 * --------------
		 * 1	student_1
		 * 2	student_2
		 * 3	student_3
		 * 
		 */
		for(Integer key : map_1.keySet()) {
			Student student = map_1.get(key);
			System.out.print(key + "   " + student.getId() + student.getAge() + student.getName());
		}
		
		// Lambda
		map_1.keySet().forEach(key -> {
			Student student = map_1.get(key);
			System.out.print(key + "   " + student.getId() + student.getAge() + student.getName());
		});
		
		
		/**
		 * Assignment
		 * 
		 * CASE 1
		 * 研究两个 SB 对象，使用 append方法，
		 * 对比 String 
		 * 
		 * CASE 2
		 * 任意定义一个数组和集合，测试“追加”元素的方法。
		 * 
		 * CASE 3
		 * 复习JDBC Mysql，测试本机数据库环境连通。
		 * 写一个 DAO & SQL 访问数据到 JavaBean 打印输出。
		 * 
		 * CASE 4
		 * 在虚拟机中安装 Linux Mysql，
		 * 并在系统中测试数据库连通
		 * 创建一个Student表
		 * 
		 * CASE 5
		 * Linux 中创建 hadoop 用户，密码 123456
		 * 使用 hadoop 用户测试以下命令
		 * ls ll pwd cd psswd 
		 * 
		 * CASE 6 
		 * 准备卓越项目主题，统一报送给学委
		 * 准备项目立项文档
		 * 
		 * CASE 7
		 * Eclipse 新建 Web 工程
		 * 
		 * 项目命名规范
		 * mhys-dsj2020-1
		 * 
		 * 包命名规范
		 * com.mhys.main
		 * com.mhys.dao
		 * com.mhys.pojo
		 * com.mhys.service
		 * com.mhys.test
		 * 
		 */
		System.out.println();
		for(int i = 0; i < 3; i++) {
			System.out.println("懒！");
		}
	}
	
}

