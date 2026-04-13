package com.smartplanner.web.service;

import com.smartplanner.web.dao.UserDAO;
import com.smartplanner.web.model.User;

/**
 * ============================================================
 * CLASS: UserService
 * ============================================================
 * PURPOSE
 * -------
 * Service layer responsible for user management.
 * *
 * RESPONSIBILITIES
 * ----------------
 * - Register new users
 * - Authenticate (login) users
 * *
 * DESIGN
 * ------
 * - Users are no longer stored in memory
 * - All user data is stored in the database
 * - Uses UserDAO for persistence
 * *
 * ARCHITECTURE ROLE
 * -----------------
 * Main → UserService → UserDAO → Database
 * *
 * ============================================================
 */

public class UserService {
    private final UserDAO userDAO;

    // Constructor initializes DAO
    public UserService() {
        userDAO = new UserDAO();
    }

    /*
     * ========================================================
     * METHOD: registerUser()
     * ========================================================
     * PURPOSE
     * -------
     * Registers a new user in the database.
     */
    public void registerUser(String username, String password) {
        // Check if user already exists
        User existingUser = userDAO.getUserByUsername(username);

        if (existingUser != null) {
            System.out.println("\nUser already exists.");
            return;
        }

        // Save user to database
        userDAO.saveUser(new User(username, password));
        System.out.println("\nUser registered successfully.");
    }

    /*
     * ========================================================
     * METHOD: loginUser()
     * ========================================================
     * PURPOSE
     * -------
     * Authenticates a user using database records.
     */
    public User loginUser(String username, String password) {
        User user = userDAO.getUser(username, password);

        if (user != null) {
            System.out.println("\nLogin successful.");
            return user;
        }
        System.out.println("\nInvalid username or password.");
        return null;
    }
}