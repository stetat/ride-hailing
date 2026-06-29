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


@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
	
	private final PassengerService passengerService;
	
	public PassengerController(PassengerService passengerService) {
		this.passengerService = passengerService;
	}
	
	@GetMapping
	public List<Passenger> getAllPassengers() {
		return passengerService.getAll();
	}
	
	@PostMapping
	public Passenger createPassenger(@RequestBody PassengerRequestDTO dto) {
		return passengerService.create(dto);
	}
	
	@GetMapping("/{id}")
	public Passenger getPassengerById(@PathVariable Long id) {
		return passengerService.getById(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePassenger(@PathVariable Long id) {
		passengerService.delete(id);
	}
	
	@PutMapping("/{id}")
	public Passenger updatePassenger(@PathVariable Long id, @RequestBody PassengerRequestDTO dto) {
		return passengerService.update(id, dto);
	}

}
