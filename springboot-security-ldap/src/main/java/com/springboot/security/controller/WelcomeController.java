package com.springboot.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bishesh
 */
@RestController
public class WelcomeController {

    @GetMapping("/")
    public String welcome()
    {
        return "Welcome";
    }

}
