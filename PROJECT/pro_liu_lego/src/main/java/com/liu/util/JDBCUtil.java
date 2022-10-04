package com.liu.util;

import java.sql.Connection;
import java.sql.DriverManager;
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
 */
public class JDBCUtil {
	
	/**
	 * 第二部分 背写下来5分，共~10分
	 */
	
	// jdbc:mysql://localhost:3306/source_db?useUnicode=true&amp;characterEncoding=UTF-8&amp;useSSL=false
	private static String url = "jdbc:mysql://localhost:3306/source_db";
	private static String userName = "root";
	private static String password = "12345678";
	
	// Msqyl 5.0 驱动
//	private static String driver = "com.mysql.jdbc.Driver";
	// Mysql 8.0 同学改这里
	 private static String driver = "com.mysql.cj.jdbc.Driver";

	// 加载驱动
	static {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 连接
	public static Connection getConnection() throws SQLException {
		
		return DriverManager.getConnection(url, userName, password);
	}

}
