package com.reservation.repository;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.reservation.pojo.Customer;

public interface ICustomerRepository {

	//@PreAuthorize("hasAuthority('admin')")
	public List<Customer> getAll(String status) ;
	 public void saveCustomer(Customer customer);
}
