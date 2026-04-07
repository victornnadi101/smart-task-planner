package com.smartplanner.web.model;
import java.util.ArrayList;
import java.util.List;

/**
 * ========================================================
 * CLASS: User
 * ========================================================
 * PURPOSE
 * -------
 * Represents a user in the system.
 * Each user has:
 * - username
 * - password
 * - their own list of tasks
 */

public class User {
    private final String username;
    private final String password;

    // Each user has their own tasks
    private final List<Task> tasks;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.tasks = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
