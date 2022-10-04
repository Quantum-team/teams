package com.liu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author 刘老师
 * 
 * - 源码请所有同学合理使用，禁止非学习用途。
 * - 参照源码多想多练多Debug，杜绝无脑Copy！
 * - 有问题找学委统一汇总，课堂答疑，也可到办公室问我。
 * - 小红书|微信视频号 @老刘编程 三连到位，禁止下次一定！
 *
 * 通用 Dao 封装公共属性常量
 */

public interface IGeneralDao<T> {
	
	public static final String PRIMARY_KEY 		= "id";
	public static final String T_PERSON 		= "t_person";
	public static final String T_USER 			= "t_user";

	public T mapResult(final ResultSet rs) throws SQLException;
	
}
