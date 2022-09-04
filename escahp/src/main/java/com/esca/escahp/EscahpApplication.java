package com.esca.escahp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EscahpApplication {
	public static void main(String[] args) {
		SpringApplication.run(EscahpApplication.class, args);
	}
}
