package com.springboot.security.controller;

import com.springboot.security.entity.User;
import com.springboot.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bishesh
 */
@RestController
public class JdbcSpringbootController {
//
//    @Autowired
//    private UserRepository userRepository;

    @GetMapping("/")
    public String welcome () {
        return "<h1>Welcome</h1>";
    }


    @GetMapping("/user")
    public String welcomeUser () {
        return "<h1>Welcome User</h1>";
    }


    @GetMapping("/admin")
    public String welcomeAdmin () {
        return "<h1>Welcome Admin</h1>";
    }

//    @PostMapping("/createUser")
//    public User createUser () {
//        User user = new User();
//        user.setUserName("foo");
//        user.setPassword("foo");
//        user.setActive(true);
//        user.setRoles("ROLE_ADMIN");
//
//        return userRepository.save(user);
//
//    }
}
