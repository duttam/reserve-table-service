package com.reservation.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ReservationUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
    JwtProvider jwtProvider;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> users=userRepository.getUser(username);
		if(users!=null && !users.isEmpty()) {
			User user=users.get(0);
			 return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
			            .password(user.getPassword())
			            .authorities(user.getRole())
			            .accountExpired(false)
			            .accountLocked(false)
			            .credentialsExpired(false)
			            .disabled(!user.getEnable())
			            .build();
			
		}
		else 
		{
			throw new UsernameNotFoundException("username not found");
		}
		
	}

	public UserDetails loadUserByJwtToken(String jwtToken) {
        if (jwtProvider.isValidToken(jwtToken)) {
            return org.springframework.security.core.userdetails.User.
                withUsername(jwtProvider.getUsername(jwtToken))
                .authorities(jwtProvider.getRoles(jwtToken))
                .password("") //token does not have password but field may not be empty
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
        }
        
        return null;
    }
	
}
