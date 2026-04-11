package com.smartplanner.web.service;

import com.smartplanner.web.dao.TaskDAO;
import com.smartplanner.web.model.Status;
import com.smartplanner.web.model.Task;
import com.smartplanner.web.model.User;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * ================================================================
 * CLASS: TaskService
 * ================================================================
 * PURPOSE
 * -------
 * Service layer responsible for task management logic.
 * *
 * RESPONSIBILITIES
 * ----------------
 * - Add tasks
 * - View tasks
 * - Complete tasks
 * - Delete tasks
 * - Search tasks
 * - Filter tasks
 * *
 * DESIGN
 * ------
 * - NO in-memory storage anymore
 * - ALL tasks are stored in the database
 * - Uses TaskDAO for all operations
 * *
 * ARCHITECTURE
 * ------------
 * Main → UserService → User
 *                       ↓
 *                  TaskService → TaskDAO → Database
 * *
 * ================================================================
 */
@Service
public class TaskService {
    private final TaskDAO taskDAO;

    // Constructor initializes DAO
    public TaskService() {
        taskDAO = new TaskDAO();
    }

    /*
     * =====================================================
     * METHOD: addTask()
     * =====================================================
     * *
     * Adds a task for a specific user into the database.
     */
    public void addTask(User user, Task task) {
        taskDAO.saveTask(user.getUsername(), task);
        System.out.println("\nTask added successfully.");
    }

    /*
     * ============================================================
     * METHOD: getTasks()
     * ============================================================
     * PURPOSE
     * -------
     * Retrieves all tasks belonging to a specific user from the database.
     *
     * PARAMETERS
     * ----------
     * User user → the user whose tasks are being retrieved
     *
     * RETURNS
     * -------
     * List<Task> → list of tasks associated with the user
     *
     * DESIGN NOTE
     * -----------
     * This method delegates data retrieval to TaskDAO,
     * ensuring separation between business logic and data access.
     */
    public List<Task> getTasks(User user) {
        return taskDAO.getTasks(user.getUsername());
    }

    /*
     * =====================================================
     * METHOD: viewTasks()
     * =====================================================
     *
     * Displays all tasks for a specific user.
     */
    public void viewTasks(User user) {
        List<Task> tasks = taskDAO.getTasks(user.getUsername());

        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks found!");
            return;
        }

        System.out.println("===================================");
        System.out.println("|          Task List              |");
        System.out.println("===================================");
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-5s | %-20s | %-12s | %-10s | %-10s\n",
                "ID", "Title", "Due Date", "Priority", "Status");
        System.out.println("------------------------------------------------------------------");

        for (Task task : tasks) {
            // Tasks displayed with demarcation between rows.
            task.displayTask();
            System.out.println("------------------------------------------------------------------");

        }
    }

    /*
     * ======================================================
     * METHOD: completeTask()
     * ======================================================
     *
     * Marks a task as completed in the database.
     */
    public void completeTask(int taskId) {
        taskDAO.completeTask(taskId);
        System.out.println("\nTask completed successfully.");
    }

    /*
     * ========================================================
     * METHOD: deleteTask()
     * ========================================================
     *
     * Deletes a task from the database.
     */
    public void deleteTask(int taskId) {
        taskDAO.deleteTask(taskId);
        System.out.println("\nTask deleted successfully.");
    }

    /*
     * ============================================================
     * METHOD: searchTasks()
     * ============================================================
     *
     * Searches tasks by keyword for a user.
     */
    public void searchTasks(User user, String keyword) {
        List<Task> tasks = taskDAO.getTasks(user.getUsername());

        boolean found = false;

        for (Task task : tasks) {
            if (task.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                task.displayTask();
                found = true;
            }
        }

        if (!found) {
            System.out.println("\nNo matching tasks found!");
        }
    }

    /*
     * ============================================================
     * METHOD: filterTasksByStatus()
     * ============================================================
     *
     * Filters tasks based on status.
     */
    public void filterTasksByStatus(User user, Status status) {
        List<Task> tasks = taskDAO.getTasks(user.getUsername());

        boolean found = false;

        for (Task task : tasks) {
            if (task.getStatus() == status) {
                task.displayTask();
                found = true;
            }
        }

        if (!found) {
            System.out.println("\nNo tasks found with status: " + status);
        }
    }
}