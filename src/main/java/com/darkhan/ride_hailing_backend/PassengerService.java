package com.darkhan.ride_hailing_backend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PassengerService {
	
	private final PassengerRepository passengerRepository;

	public PassengerService(PassengerRepository passengerRepository) {
		this.passengerRepository = passengerRepository;
	}
	
	public List<Passenger> getAll() {
		return passengerRepository.findAll();
	}
	
	public Passenger getById(Long id) {
		return passengerRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, "Passenger with ID " + id + " not found"
				));
	}
	
	public Passenger create(PassengerRequestDTO dto) {
		if(dto.getName() == null || dto.getName().trim().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passenger name can't be empty");
		}
		
		Passenger passenger = new Passenger();
		passenger.setName(dto.getName());
		
		return passengerRepository.save(passenger);
		
	}
	
	public void delete(Long id) {
		if(!passengerRepository.existsById(id) ) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Cannot delete. Passenger with ID " + id + " doesn't exist"
			);
		}
		
		passengerRepository.deleteById(id);
	}
	
	public Passenger update(Long id, PassengerRequestDTO dto) {
		
		Passenger passenger = getById(id);
		
		if(dto.getName() == null || dto.getName().trim().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passenger name cannot be empty");
			
		}
		
		passenger.setName(dto.getName());
		
		return passengerRepository.save(passenger);
		
	}
	
	
}
