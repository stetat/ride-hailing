package com.darkhan.ride_hailing_backend;

import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
	
	private final PassengerRepository passengerRepository;
	
	public PassengerController(PassengerRepository passengerRepository) {
		this.passengerRepository = passengerRepository;
	}
	
	@GetMapping
	public List<Passenger> getAllPassengers() {
		return passengerRepository.findAll();
	}
	
	@PostMapping
	public Passenger createPassenger(@RequestBody Passenger passenger) {
		return passengerRepository.save(passenger);
	}

}
