package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		
	    int i = 0;
	    while (i < 10) {
	        String password = "123456";
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String hashedPassword = passwordEncoder.encode(password);

	        System.out.println(hashedPassword);
	        i++;
	    }
		
		
		SpringApplication.run(DemoApplication.class, args);
		
		
	}

}
