package com.smartplanner.web.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * =======================================================
 * CLASS: DatabaseHelper
 * =======================================================
 * PURPOSE
 * -------
 * Handles database connection.
 * *
 * =======================================================
 */
public class DatabaseHelper {
    private static final String URL = "jdbc:sqlite:tasks:db";

    public static Connection connect() {
        try{
            return DriverManager.getConnection(URL);
        } catch(SQLException e){
            System.out.println("\nDatabase connection failed!");
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteAllTasks(String username) {
        String sql = "DELETE FROM tasks WHERE username = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.executeUpdate();

            System.out.println("All tasks deleted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
