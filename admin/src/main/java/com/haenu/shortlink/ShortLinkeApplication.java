package com.haenu.shortlink;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.haenu.shortlink.dao.mapper")
public class ShortLinkeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShortLinkeApplication.class, args);
    }
}
