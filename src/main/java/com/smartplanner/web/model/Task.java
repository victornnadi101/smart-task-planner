package com.smartplanner.web.model;
import java.time.LocalDate;

/**
 * ======================================================================
 * CLASS: Task
 * ======================================================================
 * *
 * PURPOSE
 * -------
 * This class represents a single task in the Smart Task Planner system.
 * Each Task object models a real task created by a user.
 * *
 * RESPONSIBILITIES
 * ----------------
 * A Task object stores:
 * - Automatically generated task ID
 * - Task title
 * - Task description
 * - Due date
 * - Task priority (using enum Priority)
 * - Task status (using enum Status)
 * *
 * DESIGN NOTES
 * ------------
 * - Task IDs are auto-generated using a static counter
 * - Priority and Status use enums to enforce valid values
 * - Each task belongs to a specific user (handled in User class)
 * *
 * EXAMPLE TASK OBJECT
 * -------------------
 * Task
 *   taskId = 1
 *   title = "Finish Software Engineering Assignment"
 *   description = "Complete UML class diagram"
 *   dueDate = 2026-03-15
 *   priority = HIGH
 *   status = PENDING
 * *
 * ======================================================================
 */

public class Task {

    // Static counter for automatic ID generation
    private static int idCounter = 1;

    // Unique identifier for each task.
    // This allows the system to find a task later.
    private int taskId;

    // Short name for the task
    private final String title;

    // Detailed explanation of the task.
    private final String description;

    /*
     * Due date for completing the task
     *
     * Java's LocalDate class is used because it represents
     * a date without time or timezone.
     */
    private final LocalDate dueDate;

    /*
     * Priority level of the task.
     *
     * Example values:
     * LOW
     * MEDIUM
     * HIGH
     */
    private final Priority priority;

    /*
     * Current status of the task.
     *
     * Possible values:
     * PENDING
     * COMPLETED
     */
    private Status status;

    /*
     * ==================================================================
     * CONSTRUCTOR
     * ==================================================================
     * This is called when a new Task object is created.
     *
     * Example:
     * Task task = new Task("Study", "Read chapter 5", date, "Priority.HIGH");
     *
     * This initializes the task with initial values.
     */
    public Task(String title, String description, LocalDate dueDate, Priority priority) {
        this.taskId = idCounter++;  // auto increment
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = Status.PENDING;   // Every task starts as PENDING by default
    }

    /*
     * ==================================================================
     * GETTER METHODS
     * ==================================================================
     * These getter methods allow other classes to read private data safely.
     * Fields are to remain private in order to protect the internal state of the object.
     */

    // Used only for database mapping
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Priority getPriority() { return priority; }

    public Status getStatus() { return status; }

    /*
     * ===================================================================
     * markCompleted()
     * ===================================================================
     * This method changes the task status to COMPLETED.
     * It will be called when the user finishes the task.
     */
    public void markCompleted() {
        this.status = Status.COMPLETED;
    }

    /*
     * ===================================================================
     * displayTask()
     * ===================================================================
     * This method prints all the task information to the console.
     * It is useful for testing and debugging the system.
     */
    public void displayTask() {
        System.out.printf(
                "%-5d | %-20s | %-12s | %-10s | %-10s\n",
                taskId,
                title,
                dueDate,
                priority,
                status
        );
    }
}
