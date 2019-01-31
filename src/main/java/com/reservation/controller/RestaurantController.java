package com.reservation.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.pojo.Customer;
import com.reservation.pojo.Restaurant;
import com.reservation.repository.RestaurantRepository;


@RestController
@RequestMapping("/restaurants")
@CrossOrigin
public class RestaurantController {
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getAllRestaurants(){
		logger.info("inside getallrestaurants");
		List<Restaurant> restaurants=restaurantRepository.getAll();
		if(restaurants.isEmpty()){
			return new ResponseEntity<List<Restaurant>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		logger.info("finished getallrestaurants");
		return new ResponseEntity<>(restaurants, HttpStatus.OK);
		
		
	}

}
