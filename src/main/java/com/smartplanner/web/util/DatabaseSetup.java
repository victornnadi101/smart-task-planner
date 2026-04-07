package com.smartplanner.web.util;

import java.sql.Connection;
import java.sql.Statement;

/**
 * ============================================================
 * CLASS: DatabaseSetup
 * ============================================================
 * PURPOSE
 * -------
 * Creates tables if they do not exist.
 * *
 * ============================================================
 */

public class DatabaseSetup {
    public static void initialize() {
        String userTable = "CREATE TABLE IF NOT EXISTS users(username TEXT PRIMARY KEY, password TEXT)";
        String taskTable = """
                CREATE TABLE IF NOT EXISTS tasks(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT,
                title TEXT,
                description TEXT,
                dueDate TEXT,
                priority TEXT,
                status TEXT
                );
                """;
        try(Connection conn = DatabaseHelper.connect();
            Statement stmt = conn.createStatement()) {

            stmt.execute(userTable);
            stmt.execute(taskTable);

            String setStartId = "INSERT OR IGNORE INTO sqlite_sequence(name, seq) VALUES ('tasks', 100);";
            stmt.execute(setStartId);

            System.out.println("\nDatabase connection established!");
        } catch(Exception e) {
            System.out.println("\nError creating tables!");
            e.printStackTrace();
        }
    }
}
