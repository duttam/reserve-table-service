package com.reservation.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.pojo.Customer;

import com.reservation.repository.CustomerRepository;
import com.reservation.repository.ICustomerRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/customers")
@Api(value="customerwebservice",description="Webservice end point to manage customers ")
@CrossOrigin
public class CustomerController {
	@Autowired
	private ICustomerRepository customerRepository;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize("hasAuthority('admin')")
	@ApiOperation(value="given a status return customers")
	public ResponseEntity<?> getActiveCustomers(@ApiParam(name="status", value="status of a customer whether it's active or inactive", required=true)
	final @RequestParam("status") @NotNull String status){
		logger.info("status :{} ", status);
		List<Customer> customers=customerRepository.getAll(status);
		if(customers.isEmpty()){
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		logger.info("finished getActiveCustomers");
		return new ResponseEntity<>(customers, HttpStatus.OK);
		
	}
	

}
