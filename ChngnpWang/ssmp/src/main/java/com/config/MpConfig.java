package com.config;

/**
 * ClassName:MpConfig
 * PackageName:com.config
 * Description: 分页的拦截器，实现分页查询功能
 *
 * @date:2022/5/11 15:46
 * @author: YuanCoding
 */

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MpConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        //拦截器2
//        interceptor.addInnerInterceptor(new PaginationInnerInterceptor1());
        return interceptor;
    }

}
