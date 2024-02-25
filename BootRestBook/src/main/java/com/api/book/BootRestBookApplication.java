package com.api.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages = {"com.api.book.controller", "com.api.book.entities","com.api.book.services","com.api.book.services.dao"})
@SpringBootApplication
public class BootRestBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootRestBookApplication.class, args);
	}
}
