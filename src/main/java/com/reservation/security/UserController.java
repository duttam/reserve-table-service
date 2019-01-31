package com.reservation.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

   @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid User user) {
       String jwt =  userService.signin(user.getUsername(), user.getPassword());
       System.out.println("JWT "+jwt);
       
       return ResponseEntity.ok(new JwtResponse(jwt));
    }

    

//    @GetMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public List<User> getAllUsers() {
//        return userService.getAll();
//    }

}