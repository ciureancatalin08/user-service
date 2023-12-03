package com.example.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserService {

	public static void main(String[] args) {
		System.out.println("bugService");

		SpringApplication.run(UserService.class, args);
	}
}
