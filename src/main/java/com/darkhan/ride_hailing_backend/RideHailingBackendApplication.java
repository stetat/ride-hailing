package com.darkhan.ride_hailing_backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RideHailingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RideHailingBackendApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demoData(PassengerRepository repository) {
		return (args) -> {
			System.out.println("Seeding dummy data");
			
			Passenger p1 = new Passenger();
			p1.setName("Darkhan");
			p1.setEmail("abc@gmail.com");
			
			Passenger p2 = new Passenger("Dauren", "123@gmail.com", "77777");
			
			repository.save(p1);
			repository.save(p2);
			
			System.out.println("Dummy passengers saved");
			
		};
	}

}
