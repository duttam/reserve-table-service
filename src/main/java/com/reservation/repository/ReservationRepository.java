package com.reservation.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.reservation.pojo.Customer;
import com.reservation.pojo.Reservation;
import com.reservation.pojo.Restaurant;
@Repository
public class ReservationRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String saveReservation(Reservation reservation) {
		String reservationid=UUID.randomUUID().toString();
		int rows=jdbcTemplate.update("insert into reservation(reservationid,reservationdate,guestcount,customerid,restaurantid) "
				+ "values(?,?,?,?,?)", 
				new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, reservationid);
				SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String reserveDate=timeformat.format(reservation.getReservationDate());
				ps.setString(2,reserveDate);
				ps.setInt(3, reservation.getNoOfGuest());
				ps.setString(4, reservation.getReserveBy().getCustomerId());
				ps.setString(5, reservation.getReservedAt().getRestaurantId());
				
			}
		});
		if(rows>0)
			return reservationid;
		
		return null;
		
		
	}
	public List<Reservation> getAll() {
		return jdbcTemplate.query("SELECT r.reservationid, r.reservationdate, r.guestcount ,r.restaurantid,"
				+ "c.customerfirstname,c.customerid, c.customerlastname,res.restaurantname " + 
				"from reservation as r " + 
				"LEFT join customer as c on r.customerid=c.customerid " + 
				"LEFT join restaurant as res on r.restaurantid=res.restaurantid", new RowMapper<Reservation>() {

					@Override
					public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
						Customer c=new Customer();
						c.setCustomerId(rs.getString("customerid"));
						c.setFirstname(rs.getString("customerfirstname"));
						c.setLastname(rs.getString("customerlastname"));
						
						Restaurant r=new Restaurant();
						r.setRestaurantId(rs.getString("restaurantid"));
						r.setRestaurantName(rs.getString("restaurantname"));
						
						Reservation res=new Reservation();
						res.setReservationId(rs.getString("reservationid"));
						res.setReserveBy(c);
						res.setReservedAt(r);
						res.setNoOfGuest(rs.getInt("guestcount"));
						SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						try {
							res.setReservationDate(timeformat.parse(rs.getString("reservationdate")));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						return res;
					}
				}) ;
		
	}
	public Reservation findById(String id) {
		
		 
			List<Reservation> reservations=jdbcTemplate.query("SELECT r.reservationid, r.reservationdate, r.guestcount ,r.restaurantid,"
				+ "c.customerfirstname,c.customerid, c.customerlastname,res.restaurantname " + 
				"from reservation as r " + 
				"LEFT join customer as c on r.customerid=c.customerid " + 
				"LEFT join restaurant as res on r.restaurantid=res.restaurantid"
				+ " where r.reservationid=?", new Object[]{id},new RowMapper<Reservation>() {

					@Override
					public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
						Customer c=new Customer();
						c.setCustomerId(rs.getString("customerid"));
						c.setFirstname(rs.getString("customerfirstname"));
						c.setLastname(rs.getString("customerlastname"));
						
						Restaurant r=new Restaurant();
						r.setRestaurantId(rs.getString("restaurantid"));
						r.setRestaurantName(rs.getString("restaurantname"));
						
						Reservation res=new Reservation();
						res.setReservationId(rs.getString("reservationid"));
						res.setReserveBy(c);
						res.setReservedAt(r);
						res.setNoOfGuest(rs.getInt("guestcount"));
						SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						try {
							res.setReservationDate(timeformat.parse(rs.getString("reservationdate")));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						return res;
					}
				}) ;
			if(reservations != null && !reservations.isEmpty()) {
				return reservations.get(0);
			}
			return null;
	}

}
