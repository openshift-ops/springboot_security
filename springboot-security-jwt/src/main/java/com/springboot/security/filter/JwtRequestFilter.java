package com.springboot.security.filter;

import com.springboot.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        //1.WE NEED TO HAVE THIS FILTER IN ORDER TO INTERCEPT ALL REQUEST
        //2.WE NEED TO LOOK AT THE HEADER EXTRACT THE JWT AND THEN VALIDATE IT
        //3.FOR VALIDATION WE NEED THE USERDETAILS FROM THE USER DETAILS SERVICE
        //4.ONCE VALIDATION IS COMPLETE WE NEED TO SET THE AUTHENTICATION OBJECT BACK IN THE SECURITY CONTEXT HOLDER


        String token = null;
        String userName = null;

        System.out.println("Hitting");
        //get the header
        final String jwtHeader = request.getHeader("Authorization");

        if(jwtHeader != null && jwtHeader.startsWith("Bearer ")) {
            token = jwtHeader.substring(7);
            userName = jwtUtil.extractUserName(token);
        }

//check if the username is not null then the token is also nut null by default and check if the user has not been authenticated by checking if there is not authentication object inside the SecurityContext
           if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null)
           {
               UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
               //validate the token
               if(jwtUtil.validateToken(token,userDetails))
               {
                   //here we are taking over the authentication process from spring security
                   //create the authentication object
                   UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                   //WE HAVE TO ADD THE CURRENT REQUEST TO THE AUTHENTICATION
                   authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                   //now we have to add the authentication to the security context
                   System.out.println("Hitting 2");
                   SecurityContextHolder.getContext().setAuthentication(authenticationToken);
               }
           }
//we have to now pass it to next filter in the chain
        filterChain.doFilter(request,response);
    }
}
