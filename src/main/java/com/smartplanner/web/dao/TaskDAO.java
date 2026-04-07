package com.smartplanner.web.dao;

import com.smartplanner.web.model.Priority;
import com.smartplanner.web.model.Task;
import com.smartplanner.web.util.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * CLASS: TaskDAO
 * ============================================================
 * PURPOSE
 * -------
 * Handles database operations for tasks.
 * *
 * RESPONSIBILITIES
 * ----------------
 * - Save tasks to database
 * - Retrieve tasks for a specific user
 * - Delete tasks
 * - Update task status
 * *
 * ============================================================
 */

public class TaskDAO {
    //Save task to database
    public void saveTask(String username, Task task) {

        String sql = "INSERT INTO tasks(username, title, description, dueDate, priority, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, task.getTitle());
            stmt.setString(3, task.getDescription());
            stmt.setString(4, task.getDueDate().toString());
            stmt.setString(5, task.getPriority().name());
            stmt.setString(6, task.getStatus().name());

            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("\nError saving task.");
        }
    }

    //Retrieve all tasks for a user
    public List<Task> getTasks(String username) {
        List<Task> tasks = new ArrayList<>();

        String sql = "SELECT * FROM tasks WHERE username = ?";

        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Task task = new Task(
                        rs.getString("title"),
                        rs.getString("description"),
                        LocalDate.parse(rs.getString("dueDate")),
                        Priority.valueOf(rs.getString("priority"))
                );

                // Override auto ID with DB ID
                int dbId = rs.getInt("id");
                task.setTaskId(dbId);

                if (rs.getString("status").equals("COMPLETED")) {
                    task.markCompleted();
                }
                tasks.add(task);
            }
        } catch (Exception e) {
            System.out.println("\nError retrieving tasks.");
        }
        return tasks;
    }

    // Delete task
    public void deleteTask(int taskId) {
        String sql = "DELETE FROM tasks WHERE id = ?";

        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, taskId);
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("\nError deleting task.");
        }
    }

    //Update task status
    public void completeTask(int taskId) {
        String sql = "UPDATE tasks SET status = 'COMPLETED' WHERE id = ?";

        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(101, taskId);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("\nError updating task.");
        }
    }
}
