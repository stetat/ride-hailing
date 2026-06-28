package com.darkhan.ride_hailing_backend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/trips")
public class TripController {
	private final TripRepository tripRepository;
	private final PassengerRepository passengerRepository;
	
	public TripController(TripRepository tripRepository, PassengerRepository passengerRepository) {
		this.tripRepository = tripRepository;
		this.passengerRepository = passengerRepository;
	}
	
	@PostMapping("/passenger/{passengerId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Trip bookTrip(@PathVariable Long passengerId, @RequestBody Trip trip) {
		Passenger passenger = passengerRepository.findById(passengerId)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, "Cannot book trip. Passenger ID " + passengerId + " not found"
					));
		
		trip.setPassenger(passenger);
		
		return tripRepository.save(trip);
	}
	
	@GetMapping
	public List<Trip> getAllTrips() {
		return tripRepository.findAll();
	}

	public TripRepository getTripRepository() {
		return tripRepository;
	}

	public PassengerRepository getPassengerRepository() {
		return passengerRepository;
	}
	
	
}
