package com.darkhan.ride_hailing_backend;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TripService {
	
	private final TripRepository tripRepository;
	
	public TripService(TripRepository tripRepository) {
		this.tripRepository = tripRepository;
	}
	
	public Trip updateTrip(Long id, TripRequestDTO dto) {
		Trip trip = tripRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, "Trip with ID " + id + " not found"
				));
		
		if(dto.getStartLocation() != null && !dto.getStartLocation().isEmpty()) {
			trip.setStartLocation(dto.getStartLocation());
		}
		
		if(dto.getEndLocation() != null && !dto.getEndLocation().isEmpty()) {
			trip.setEndLocation(dto.getEndLocation());
		}
		
		if(dto.getPrice() != null) {
			trip.setPrice(dto.getPrice());
		}
		
		return tripRepository.save(trip);
	}
}
