package com.lab.backend;

import com.lab.backend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GerenciadorLabsApplication implements CommandLineRunner {

	@Autowired
	private AuthService authService;

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorLabsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(authService.getOrCreateProfile());
	}
}
