package com.springboot.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Bishesh
 */
//We have to tell spring-security that this is an web security configuration which means
// as this is a web application we need spring security to autheticate web requests
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    //Instead of using deprecated @WebSecurityConfigurerAdapter we can use the SecurityConfigurerAdapter
    @Override
    public void configure (AuthenticationManagerBuilder builder) throws Exception {

        //inMemory Authetication --> type of authentication
        builder.inMemoryAuthentication ()
                .withUser ("bishesh")
                .password ("bishesh")
                .roles ("USER")
                .and ()
                .withUser ("foo")
                .password ("foo")
                .roles ("ADMIN");

    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }

    //As spring expects password hashing we have to provide an password encoder as well
    @Bean
    public PasswordEncoder passwordEncoder () {
        return NoOpPasswordEncoder.getInstance ();
    }

    @Override
    public void configure (HttpSecurity httpSecurity) throws Exception{

        //HttpSecurity is used for authorizing the http requests by doing the path to role matching
        //order should be the most restrictive to less restrictive
        //we can use antpatterns to match the api paths ..ant wildcalrds ..Ex. /** matches everything
        //use hasAnyRole to add multiple roles
        httpSecurity.authorizeRequests ()
                .antMatchers ("/admin").hasRole ("ADMIN")
                .antMatchers ("/user").hasAnyRole ("USER","ADMIN")
                .antMatchers ("/").permitAll ()
                .and ().formLogin ();


    }

}


