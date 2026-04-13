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
    public ApiResponse registerUser(@RequestParam String username,
                                    @RequestParam String password) {

        userService.registerUser(username, password);

        return new ApiResponse("User registered successfully", true);
    }

    /*
     * ============================================================
     * ENDPOINT: LOGIN USER
     * ============================================================
     * URL:
     * http://localhost:8080/users/login
     */
    @GetMapping("/login")
    public ApiResponse loginUser(@RequestParam String username,
                                 @RequestParam String password) {

        User user = userService.loginUser(username, password);

        if (user != null) {
            return new ApiResponse("Login successful", true);
        } else {
            return new ApiResponse("Invalid username or password", false);
        }
    }
}