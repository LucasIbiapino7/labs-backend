package com.lab.backend;

import com.lab.backend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class GerenciadorLabsApplication {

	@Autowired
	private AuthService authService;

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorLabsApplication.class, args);
	}

}
