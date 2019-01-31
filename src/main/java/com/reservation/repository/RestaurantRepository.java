package com.reservation.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import com.reservation.pojo.Restaurant;
@Repository
public class RestaurantRepository {
	@Autowired
    private JdbcTemplate jdbcTemplate;
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
    public List<Restaurant> getAll(){
    	return jdbcTemplate.query("select restaurantid,restaurantname from restaurant" ,new RowMapper<Restaurant>() {

			@Override
			public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
				Restaurant restaurant=new Restaurant();
				restaurant.setRestaurantId(rs.getString("restaurantid"));
				restaurant.setRestaurantName(rs.getString("restaurantname"));
				return restaurant;
			}
		});
    	
    }
    
    public void saveRestaurant(Restaurant restaurant) {
    	jdbcTemplate.update("insert into restaurant(restaurantid,restaurantname) values(?,?)", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, restaurant.getRestaurantId());
				ps.setString(2, restaurant.getRestaurantName());
				
			}
		});
    	
    	
    }
}
