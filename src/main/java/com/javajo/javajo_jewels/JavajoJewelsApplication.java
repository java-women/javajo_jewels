package com.javajo.javajo_jewels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class JavajoJewelsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavajoJewelsApplication.class, args);
	}

}
