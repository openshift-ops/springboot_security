package com.springboot.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Bishesh
 */
//@EnableWebSecurity //we can use @Configuration also
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication() //for ldap authentication
        .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups") //basically to search for the groups to which the user belong analogues to roles
                .contextSource()
                .url("ldap://localhost:8389/dc=springframework,dc=org") //dc=springframework,dc=org --> root node
                .and()
                .passwordCompare()
                .passwordEncoder(new BCryptPasswordEncoder())
                .passwordAttribute("userPassword");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().fullyAuthenticated() //any reuqets should be fully authenticated
                .and().formLogin();
    }
}
