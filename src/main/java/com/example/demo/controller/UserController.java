package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Register new user
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // Login user
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User loggedIn = userService.loginUser(user.getEmail(), user.getPassword());
        if (loggedIn != null) {
            return "Login successful for user: " + loggedIn.getName();
        } else {
            return "Invalid credentials!";
        }
    }
}
