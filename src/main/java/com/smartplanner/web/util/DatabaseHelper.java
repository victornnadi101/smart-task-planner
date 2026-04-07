package com.smartplanner.web.util;

import java.sql.Connection;
import java.sql.DriverManager;
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
}
