package com.reservation.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.reservation.pojo.Customer;
import com.reservation.pojo.Restaurant;
@Repository
public class CustomerRepository implements ICustomerRepository{
	
	@Autowired	
	private JdbcTemplate jdbcTemplate;
	public List<Customer> getAll(String status) {
		return jdbcTemplate.query("select customerid,customerfirstname, customerlastname, status"
				+ " from customer where status=?", new Object[]{status}, new RowMapper<Customer>() {

					@Override
					public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
						Customer customer=new Customer();
						customer.setCustomerId(rs.getString("customerid"));
						customer.setFirstname(rs.getString("customerfirstname"));
						customer.setLastname(rs.getString("customerlastname"));
						customer.setStatus(rs.getString("status"));
						return customer;
					}
		});
		
	}
	 public void saveCustomer(Customer customer) {
	    	jdbcTemplate.update("insert into customer(customerid,customerfirstname,customerlastname,status) values(?,?,?,?)", new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, customer.getCustomerId());
					ps.setString(2, customer.getFirstname());
					ps.setString(3, customer.getLastname());
					ps.setString(4, customer.getStatus());
					
				}
			});
	    	
	    	
	    }
	

}
