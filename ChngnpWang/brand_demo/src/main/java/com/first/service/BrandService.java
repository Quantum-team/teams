package com.first.service;

import com.first.mapper.BrandMapper;
import com.first.pojo.Brand;
import com.first.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * ProjectName:   JavaWeb
 * PackageName:   com.first.service
 * ClassName:     BrandService
 * Description:  web
 *
 * @Author: ChnpngWng
 * @Date: 10/22/2022 9:32 AM
 */
public class BrandService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    /*
    * 查询所有
    * */
    public List<Brand> selectAll(){
        //调用BrandMapper.selectAll()

        //获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //获取brandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        List<Brand> brands = mapper.selectAll();
        sqlSession.close();
        return brands;
    }

    public void add(Brand brand){
        SqlSession sqlSession = factory.openSession();
        //获取brandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.add(brand);
        sqlSession.commit();
        sqlSession.close();
    }
    public Brand selectById(int id){
        //调用BrandMapper.selectAll()

        //获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //获取brandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        Brand brand = mapper.selectById(id);
        sqlSession.close();
        return brand;
    }

    public void update(Brand brand){
        //调用BrandMapper.selectAll()

        //获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //获取brandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        mapper.update(brand);
        sqlSession.commit();
        sqlSession.close();
    }
}
