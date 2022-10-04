package com.matthew.enums;

import java.util.Arrays;

/**
 * 枚举 Enum
 * - 一种特殊的类，专用于存储常量
 * - 一般用于表示和选择特定范围内的常量
 * 
 * @老刘编程 点赞关注私信获取源码
 *
 */
public enum CityEnum {
	BEI_JING, SHANG_HAI, HANG_ZHOU
}

class EmumCase {
	
	public static void main(String[] args) {
		
		/*
		 * values() 遍历枚举
		 */
		Arrays.asList(CityEnum.values()).forEach(System.out::println);
		
		System.out.println();
		
		/*
		 * 枚举与switch-case
		 */
		CityEnum city = CityEnum.BEI_JING;
		
		switch (city) {

		case BEI_JING:
			System.out.println("北京");
			break;
		case SHANG_HAI:
			System.out.println("上海");
			break;
		case HANG_ZHOU:
			System.out.println("杭州");
			break;
		default:
			System.out.println("未知城市");
			break;
		}
		
		/*
		 * Assignment
		 * - 查看API，测试枚举 values(), ordinal(), valueOf() 等方法
		 * - 编写一个季节、星座的枚举
		 */
	}
}
