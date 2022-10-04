package com.mhys.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Matthew
 *
 */
public class Util {

	public final static String UTF_8 = "UTF-8";
	public final static String _JSP = ".jsp";

	public static final String DB_URL = "jdbc:mysql://127.0.0.1/t_pro";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "12345678";

	public static Statement prepareStatement() throws SQLException {
		return DriverManager // 
				.getConnection(DB_URL, USERNAME, PASSWORD) //
				.createStatement();
	}

	public static Boolean checkNonEmpty(String string) {
		return string != null && !string.isEmpty();
	}
}
