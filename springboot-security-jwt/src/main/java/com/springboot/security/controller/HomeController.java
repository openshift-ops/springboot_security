package com.springboot.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bishesh
 */
@RestController
public class HomeController {

    @GetMapping("/hello")
    public String getHelloWorld()
    {
        return "Hello World";
    }

    //to work with jwt there many steps to follow
}
