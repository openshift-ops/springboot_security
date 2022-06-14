package com.springboot.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bishesh
 */
@RestController
public class JdbcSpringbootController {

    @GetMapping("/")
    public String welcome () {
        return "<h1>Welcome</h1>";
    }


    @GetMapping("/user")
    public String welcomeUser () {
        //System.out.println("print user " + Thread.currentThread().getName());
        return "<h1>Welcome User</h1>";
    }


    @GetMapping("/admin")
    public String welcomeAdmin () {
        //System.out.println("print admin " + Thread.currentThread().getName());
        return "<h1>Welcome Admin</h1>";
    }
}
