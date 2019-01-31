package com.reservation.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

/**
 * Filter for Java Web Token Authentication and Authorization
 *
 * Created by Mary Ellen Bowman
 */
public class JwtTokenFilter extends GenericFilterBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenFilter.class);
    private static final String BEARER = "Bearer";

    private ReservationUserDetailsService userDetailsService;

    public JwtTokenFilter(ReservationUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    

    /**
     * if present, extract the jwt token from the "Bearer <jwt>" header value.
     *
     * @param headerVal
     * @return jwt if present, empty otherwise
     */
    private String getBearerToken(String headerVal) {
        if (headerVal != null && headerVal.startsWith(BEARER)) {
            return headerVal.replace(BEARER, "").trim();
        }
        return null;
    }
//
//	@Override
//	protected void doFilterInternal(ServletRequest request,ServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		LOGGER.info("Process request to check for a JSON Web Token ");
//        //Check for Authorization:Bearer JWT
//        String headerValue = ((HttpServletRequest)request).getHeader("Authorization");
//        String token=getBearerToken(headerValue);
//            //Pull the Username and Roles from the JWT to construct the user details
//        if(token!=null && !token.isEmpty()) {
//        	
//        	 UserDetails userDetail=userDetailsService.loadUserByJwtToken(token);
//             if(userDetail!=null) {
//             	 SecurityContextHolder.getContext().setAuthentication(new 
//             			 PreAuthenticatedAuthenticationToken(userDetail, "", userDetail.getAuthorities()));
//             }
//            
//        }
//        
//
//        //move on to the next filter in the chains
//        filterChain.doFilter(request, response );
//	}



	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOGGER.info("Process request to check for a JSON Web Token ");
        //Check for Authorization:Bearer JWT
        String headerValue = ((HttpServletRequest)request).getHeader("Authorization");
        String token=getBearerToken(headerValue);
            //Pull the Username and Roles from the JWT to construct the user details
        if(token!=null && !token.isEmpty()) {
        	
        	 UserDetails userDetail=userDetailsService.loadUserByJwtToken(token);
             if(userDetail!=null) {
             	 SecurityContextHolder.getContext().setAuthentication(new 
             			 PreAuthenticatedAuthenticationToken(userDetail, "", userDetail.getAuthorities()));
             	 
//             	UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
//                SecurityContextHolder.getContext().setAuthentication(authentication);
             }
            
        }
        

        //move on to the next filter in the chains
        chain.doFilter(request, response );
	}

	
}