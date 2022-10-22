package com.first.mapper;

import com.first.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * ProjectName:   JavaWeb
 * PackageName:   com.first.mapper
 * ClassName:     BrandMapper
 * Description:  web
 *
 * @Author: ChnpngWng
 * @Date: 10/22/2022 9:27 AM
 */
public interface BrandMapper {

    /*
    * 查询所有
    * */
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();


    @Insert("insert into tb_brand values (null,#{brand_name},#{company_name},#{ordered},#{description},#{status})")
    void add(Brand brand);

    @Select("select * from tb_brand where id = #{id}")
    Brand selectById(int id);

    @Update("update tb_brand set brand_name = #{brand_name}, company_name = #{company_name}, ordered = #{ordered}, description = #{description}, status = #{status} wher id = #{id}")
    void  update(Brand brand);
}
