package org.lilyhe.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"org.lilyhe.admin.model", "org.lilyhe.admin.user"})
public class MealNannyBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MealNannyBackendApplication.class, args);
	}

}
