package com.springboot.security.controller;

import com.springboot.security.entity.AuthenticationRequest;
import com.springboot.security.entity.AuthenticationResponse;
import com.springboot.security.service.MyUserDetailsService;
import com.springboot.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bishesh
 */
@RestController
public class HomeController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping("/hello")
    public String getHelloWorld()
    {
        return "Hello World";
    }

    //to work with jwt there many steps to follow
    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@RequestBody AuthenticationRequest request) throws Exception {
        //1.We have to autheticate first in order to generate the jwt after.
        //2.We cannot do using the login form as we need to do a post request by sending the username and password as we have to generate the jwt in response for the first time

        try {
            authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        }
        catch(BadCredentialsException ex)
        {
            throw new Exception("Incorrect username and password");
        }
        //here we have to fetch the user details we can be from memeory,ldap ,or any db
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        //here we can use the jwt util class which in turn uses the jwt library to generate the jwt
        String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }
}
