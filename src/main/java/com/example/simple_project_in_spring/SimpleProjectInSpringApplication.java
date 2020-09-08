package com.example.simple_project_in_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class SimpleProjectInSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleProjectInSpringApplication.class, args);
	}

}
