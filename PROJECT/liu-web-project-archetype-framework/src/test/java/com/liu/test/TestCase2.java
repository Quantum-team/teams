package com.liu.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

//指定本类为 JUnit 单元测试类
@RunWith(SpringRunner.class)
class TestCase2 {
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
	void test_3() {
		System.out.println("test case");
		
		assert isFalse(1 > 2);
	}

	@Test
	void test_4() throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		map.put("result", true);
		String json = mapper.writeValueAsString(map);
		System.out.println(json);
	}
}
