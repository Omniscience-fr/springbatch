package com.example.springbatch;

import com.example.springbatch.service.StudentService;
import com.example.springbatch.service.impl.StudentServiceImpl;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
//开启 SpringBatch 支持
@EnableBatchProcessing
@ComponentScan("com.example.springbatch.*")
public class SpringbatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbatchApplication.class, args);
	}

}
