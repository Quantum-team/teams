package com.first.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * ProjectName:   JavaWeb
 * PackageName:   com.first.util
 * ClassName:     SqlSessionFactoryUtils
 * Description:  web
 *
 * @Author: ChnpngWng
 * @Date: 10/22/2022 9:35 AM
 */
public class SqlSessionFactoryUtils {

    private static SqlSessionFactory SqlSessionFactory;

    static {
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory(){return SqlSessionFactory;}
}
