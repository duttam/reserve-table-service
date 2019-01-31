package com.reservation.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	private UserRepository userRepository;

	private AuthenticationManager authenticationManager;



	private PasswordEncoder passwordEncoder;

	private JwtProvider jwtProvider;

	@Autowired
	public UserService(UserRepository userRepository, AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;

		this.passwordEncoder = passwordEncoder;
		this.jwtProvider = jwtProvider;
	}

	/**
	 * Sign in a user into the application, with JWT-enabled authentication
	 *
	 * @param username  username
	 * @param password  password
	 * @return Optional of the Java Web Token, empty otherwise
	 */
	public String signin(String username, String password) {
		LOGGER.info("New user attempting to sign in");
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		List<User> users = userRepository.getUser(username);
		User user=users.get(0);
		List<String> roles=Arrays.asList(user.getRole());
		String token = jwtProvider.createToken(username, roles);


		return token;
	}



	
}