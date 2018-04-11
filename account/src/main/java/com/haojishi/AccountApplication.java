package com.haojishi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@MapperScan("com.haojishi.mapper")
public class AccountApplication {
	//测试下看能不能下载下来11111111111111
	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}
}
