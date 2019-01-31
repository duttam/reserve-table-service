package com.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.reservation.pojo.Reservation;
import com.reservation.repository.ReservationRepository;

@RestController
@RequestMapping("/reserve")
@CrossOrigin
public class ReservationController {
	
	@Autowired
	private ReservationRepository reservationRepository;
	@RequestMapping( method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize("hasAuthority('admin')")
	 public ResponseEntity<?> createReservation(@RequestBody Reservation reservation,UriComponentsBuilder ucBuilder){
		
		String reservationid=reservationRepository.saveReservation(reservation);
		
		if(reservationid!=null) {
			reservation.setReservationId(reservationid);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/reserve/{id}").buildAndExpand(reservationid).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);	
			
			//return new ResponseEntity<>(reservation,HttpStatus.CREATED);
		}
		
		
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	@RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<?> getAllReservation(){
	 List<Reservation> reservation=	reservationRepository.getAll();
		return new ResponseEntity<>(reservation, HttpStatus.OK);
		
	}
	@RequestMapping(value="/{id}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getReservation(@PathVariable ("id") String id){
		
		Reservation reservation = reservationRepository.findById(id);
		if (reservation == null) {
			System.out.println("Reservation with id " + id + " not found");
			return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
		
	}

}
