package com.uts.asd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.uts.asd.mapper")
public class AsdApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsdApplication.class, args);
	}

}
