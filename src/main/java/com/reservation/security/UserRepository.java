package com.reservation.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepository {
	@Autowired	
	private JdbcTemplate jdbcTemplate;

	public List<User> getUser(String username) {
		return jdbcTemplate.query("select username,password, enable, role"
				+ " from user where username=?", new Object[]{username}, new RowMapper<User>() {

					@Override
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						User User=new User();
						User.setUsername(rs.getString("username"));
						User.setPassword(rs.getString("password"));
						User.setEnable(rs.getBoolean("enable"));
						User.setRole(rs.getString("role"));
						return User;
					}
		});
		
	}
}
