package com.miapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.miapp")
public class ProyectoarnesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoarnesApplication.class, args);
	}

}
