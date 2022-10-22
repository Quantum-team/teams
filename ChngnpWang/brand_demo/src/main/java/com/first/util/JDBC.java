package com.first.util;

import java.sql.*;

/**
 * ProjectName:   JavaWeb
 * PackageName:   com.first.util
 * ClassName:     JDBC
 * Description:  web
 *
 * @Author: ChnpngWng
 * @Date: 10/22/2022 10:41 AM
 */
public class JDBC {
    private static String urlString = "jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8";
    private static String username = "root";
    private static String passwoerd = "123456";
    private static String driver = "com.mysql.cj.jdbc.Driver";

    static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(urlString,username,passwoerd);
    }
    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        try{
            if (connection!=null) {
                connection.close();
            }
            if (preparedStatement!=null) {
                preparedStatement.close();
            }
            if (resultSet!=null) {
                resultSet.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws SQLException {
        System.out.println(getConnection());
    }
}
