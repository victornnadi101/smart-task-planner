package com.smartplanner.web.dao;

import com.smartplanner.web.model.User;
import com.smartplanner.web.util.DatabaseHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * ============================================================
 * CLASS: UserDAO
 * ============================================================
 * PURPOSE
 * -------
 * Handles database operations for users.
 * *
 * ============================================================
 */
public class UserDAO {
    public void saveUser(User user) {
        String sql = "INSERT INTO users(username, password) VALUES(?,?)";

        try(Connection conn = DatabaseHelper.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.executeUpdate();
        } catch(Exception e) {
            System.out.println("\nError saving user!");
        }
    }

    public User getUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try(Connection conn = DatabaseHelper.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                return new User(username, password);
            }
        } catch(Exception e) {
            System.out.println("\nError retrieving user!");
        }
        return null;
    }
}
