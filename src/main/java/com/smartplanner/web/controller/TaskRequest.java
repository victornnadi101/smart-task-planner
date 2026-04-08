package com.smartplanner.web.controller;

/**
 * ============================================================
 * CLASS: TaskRequest
 * ============================================================
 * PURPOSE
 * -------
 * Represents incoming JSON request for creating a task.
 * This separates API input from internal model.
 * *
 * ============================================================
 */

public class TaskRequest {
    private String username;
    private String title;
    private String description;
    private String dueDate;
    private String priority;

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
}