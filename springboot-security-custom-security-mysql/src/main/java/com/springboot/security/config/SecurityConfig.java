package com.springboot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
                .dataSource(datasource)//in real env the user details and authorities schema must have been populated beforehand in the db and we just have to pass the required data source here
                .usersByUsernameQuery("select u.my_username,u.my_password,u.enabled" +
                                              " from my_users u where u.my_username = ?")//Here we can also use custom schema as opposed to the default schema provided by springboot
                .authoritiesByUsernameQuery("select a.my_username,a.authority" +
                                                    " from my_authorities a where a.my_username = ?");
        //in the above we can use our custom schema if our table is diff or columns are diff
        //as long as we provide spring-security the above values i.e. username,password,enabled from user table
        //and username and authority from authorities table...we are fine

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

