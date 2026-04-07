package com.smartplanner.web.controller;

import com.smartplanner.web.model.User;
import com.smartplanner.web.service.UserService;

import org.springframework.web.bind.annotation.*;

/**
 * ============================================================
 * CLASS: UserController
 * ============================================================
 * PURPOSE
 * -------
 * Handles HTTP requests for user operations.
 * *
 * Provides:
 * - User registration
 * - User login
 * *
 * ============================================================
 */

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService = new UserService();

    /*
     * ============================================================
     * ENDPOINT: REGISTER USER
     * ============================================================
     * URL:
     * http://localhost:8080/users/register
     */
    @GetMapping("/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password
    ) {
        userService.registerUser(username, password);
        return "User registered successfully!";
    }

    /*
     * ============================================================
     * ENDPOINT: LOGIN USER
     * ============================================================
     * URL:
     * http://localhost:8080/users/login
     */
    @GetMapping("/login")
    public String loginUser(
            @RequestParam String username,
            @RequestParam String password
    ) {
        User user = userService.loginUser(username, password);

        if (user != null) {
            return "Login successful!";
        } else {
            return "Invalid username or password.";
        }
    }
}