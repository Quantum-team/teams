package com.liu.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * 
 * @author 刘老师
 * 
 * - 源码请所有同学合理使用，禁止非学习用途。
 * - 参照源码多想多练多Debug，杜绝无脑Copy！
 * - 有问题找学委统一汇总，课堂答疑，也可到办公室问我。
 * - 小红书|微信视频号 @老刘编程 三连到位，禁止下次一定！
 *
 * - SpringBoot 启动入口类，注意观察注解及启动项参数
 */

// 使用注解方式注册配置文件
@Configuration
// 开启组件扫描，注意包路径
@ComponentScan(basePackages = "com.liu")
// 指定基础资源配置文件
@PropertySource(value = { "classpath:application.properties" })
// 指定该类为 SpringBoot 启动入口
@SpringBootApplication
public class ServingWebContentApplication {

    @Autowired
    private Environment env;
    

    public static void main(String[] args) {

        // TODO 注意使用普通 Java 运行！！！不使用Tomcat！！！
        SpringApplication.run(ServingWebContentApplication.class, args);

    }

    // 声明并配置数据源，在 application.properties 文件中取值
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getRequiredProperty("spring.datasource.url"));
        dataSource.setUsername(env.getRequiredProperty("spring.datasource.username"));
        dataSource.setPassword(env.getRequiredProperty("spring.datasource.password"));
        
        return dataSource;
    }


    // 声明 Spring jdbcTemplate ，传入数据源
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        
        return jdbcTemplate;
    }

    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(false);
//        resolver.setPrefix("");
        resolver.setSuffix(env.getRequiredProperty("spring.freemarker.suffix"));
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath(env.getRequiredProperty("spring.freemarker.template-loader-path"));
        
        return freeMarkerConfigurer;
    }

//    @Bean
//    @Qualifier("json")
//    public ObjectMapper jsonMapper(Jackson2ObjectMapperBuilder builder) {
//        ObjectMapper mapper = builder.createXmlMapper(false).build();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        return mapper;
//    }

}
