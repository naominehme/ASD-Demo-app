package com.uts.asd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.fabiomaffioletti.firebase.EnableFirebaseRepositories;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;

@SpringBootApplication
@EnableFirebaseRepositories
@EnableAsync
@MapperScan(basePackages = "com.uts.asd.mapper")
public class AsdApplication extends SpringBootServletInitializer {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AsdApplication.class, args);
	}

}
