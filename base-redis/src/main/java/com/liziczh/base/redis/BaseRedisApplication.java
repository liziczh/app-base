package com.liziczh.base.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.liziczh.**.redis" })
public class BaseRedisApplication {
	public static void main(String[] args) {
		SpringApplication.run(BaseRedisApplication.class, args);
	}
}
