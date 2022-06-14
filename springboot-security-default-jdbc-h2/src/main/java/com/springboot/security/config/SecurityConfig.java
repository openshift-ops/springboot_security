package com.springboot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * @author Bishesh
 */
@EnableWebSecurity
//indicates spring-security that web security configuration are done here so that spring-security should provide the required objs to configure
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource datasource; //as h2 is the embedded database here so spring-autoconfiguration will automatically provide configure the h2 database and provide the required datasource


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication() //using jdbc authetication
                .dataSource(datasource) //needs the required data source to fethc the  user crdentials and store it as well
                .withDefaultSchema()  //spring-security provides an default schema to create the user and authorization tables by itseld --> not rcommended
                .withUser(
                        User.withUsername("bishesh")
                                .password("bishesh")
                                .roles("USER")

                )
                .withUser(
                        User.withUsername("foo")
                                .password("foo")
                                .roles("ADMIN")
                );

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                .antMatchers("/").permitAll()
                .and().formLogin();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}

