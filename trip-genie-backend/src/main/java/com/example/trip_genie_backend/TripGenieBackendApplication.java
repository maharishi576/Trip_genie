package com.example.trip_genie_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableScheduling
public class TripGenieBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripGenieBackendApplication.class, args);
	}

}
