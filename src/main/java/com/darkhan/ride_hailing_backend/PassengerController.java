package com.darkhan.ride_hailing_backend;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
	
	@GetMapping("/{id}")
	public Passenger getPassengerById(@PathVariable Long id) {
		return passengerRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, "Passenger with ID " + id + " not found"
				));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePassenger(@PathVariable Long id) {
		if(!passengerRepository.existsById(id)) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Cannot delete. Passenger with ID " + id + " does not exist");
			
		}
		passengerRepository.deleteById(id);
	}

}
