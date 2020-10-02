package com.example.myarticle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MyarticleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyarticleApplication.class, args);
	}

}
