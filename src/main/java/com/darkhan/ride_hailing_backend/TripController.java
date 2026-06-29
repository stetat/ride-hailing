package com.darkhan.ride_hailing_backend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	private final TripService tripService;
	
	public TripController(TripRepository tripRepository, PassengerRepository passengerRepository, TripService tripService) {
		this.tripRepository = tripRepository;
		this.passengerRepository = passengerRepository;
		this.tripService = tripService;
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
	
	@GetMapping("/{tripId}")
	public Trip getTripById(@PathVariable Long tripId) {
		return tripRepository.findById(tripId)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, "Trip with ID " + tripId + " not found"
				));
		
	}
	
	@DeleteMapping("/{tripId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTrip(@PathVariable Long tripId) {
		if(!tripRepository.existsById(tripId)) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Cannot delete. Trip with ID " + tripId + " doesn't exist"
			);
		}
		
		tripRepository.deleteById(tripId);
	}
	
	@PutMapping("/{id}")
	public Trip updateTrip(@PathVariable Long id, @RequestBody TripRequestDTO dto) {
		return tripService.updateTrip(id, dto);
	}

	public TripRepository getTripRepository() {
		return tripRepository;
	}

	public PassengerRepository getPassengerRepository() {
		return passengerRepository;
	}

	public TripService getTripService() {
		return tripService;
	}
	
	
	
}
