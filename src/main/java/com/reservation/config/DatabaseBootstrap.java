package com.reservation.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.reservation.pojo.Customer;
import com.reservation.pojo.Restaurant;
import com.reservation.repository.CustomerRepository;
import com.reservation.repository.RestaurantRepository;







//@Component
public class DatabaseBootstrap implements CommandLineRunner {

	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("Bootstrapping data: ");
		
		System.out.println();
		/*
		 * for (int i = 1; i < 4; i++) { Restaurant r= new
		 * Restaurant(UUID.randomUUID().toString(),"restaurant-"+i);
		 * restaurantRepository.saveRestaurant(r); }
		 * System.out.println(restaurantRepository.getAll().size());
		 */
		String[] status= {"active","inactive"};
		
		for (int i = 1; i < 4; i++) {
			Customer customer=new Customer(UUID.randomUUID().toString(),"c"+i+"-firstname","c"+i+"-lastname", status[new Random().nextInt(1)]);
			customerRepository.saveCustomer(customer);
		}
			
		
		
	}
	

	
}
