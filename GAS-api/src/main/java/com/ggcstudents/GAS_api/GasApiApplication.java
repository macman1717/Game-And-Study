package com.ggcstudents.GAS_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class GasApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GasApiApplication.class, args);
	}

}
