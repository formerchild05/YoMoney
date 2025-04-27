package com.example.springtest;

import com.example.springtest.service.DBservice;
import com.example.springtest.testingArea.father;
import com.example.springtest.testingArea.takeDI;
import com.example.springtest.testingArea.tet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringTestApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringTestApplication.class, args);
//		DBservice a = context.getBean(DBservice.class);
//		System.out.println(a.getAllUsers());
	}

}
