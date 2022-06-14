package com.springboot.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bishesh
 */
@RestController
public class BasicSecurityController {

    @GetMapping("/")
    public String welcome()
    {
        return "<h1>Welcome</h1>";
    }

    @GetMapping("/user")
    public String welcomeUser()
    {
        return "<h1>Welcome USER</h1>";
    }

    @GetMapping("/admin")
    public String welcomeAdmin()
    {
        return "<h1>Welcome ADMIN</h1>";
    }
}
