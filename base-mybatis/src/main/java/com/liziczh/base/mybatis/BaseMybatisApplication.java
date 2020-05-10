package com.liziczh.base.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.liziczh.**.mapper")
@EntityScan(basePackages = "com.liziczh.**.entity")
@EnableCaching
public class BaseMybatisApplication {
	public static void main(String[] args) {
		SpringApplication.run(BaseMybatisApplication.class, args);
	}
}
