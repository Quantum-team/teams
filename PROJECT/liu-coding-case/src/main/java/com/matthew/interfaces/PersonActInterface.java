package com.matthew.interfaces;

import com.liu.cases.Person;

/**
 * 
 * 接口 interface 
 * 与
 * 实现 implements
 * 
 * - 接口interface 是完全抽象化的抽象类 abstract class
 * - 只能声明常量 static final 和（隐式）抽象方法
 * - 普通类只能继承一个类，但可以实现多个接口 implements
 * - 接口极大地提升了类的拓展性，可以自由赋予相关功能
 * - Java8 之后允许接口声明 default 实现的方法
 * - 接口可以多继承 
 * 
 * 
 * 设计接口继承、实现关系
 * - 人类活动接口	PersonActInterface
 * - 就业接口		WorkingInterface extends PersonActInterface
 * - 商业活动接口	BizInterface extends PersonActInterface
 * 
 * @老刘编程 点赞关注私信获取源码
 *
 * 接口 表示人类活动
 */
public interface PersonActInterface {

	// 静态常量 
	public static final Integer ADULT_AGE = 18;
	
	// 接口中实现的方法必须用 default 声明
	default public String getCertId(Person person) {
		return "id:" + person.getId() + person.getName();
	}
}
