package com.example.trip_genie_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TripGenieBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripGenieBackendApplication.class, args);
	}

}
