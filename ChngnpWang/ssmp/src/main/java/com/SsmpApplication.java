package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dao")
public class SsmpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsmpApplication.class, args);
    }

}
