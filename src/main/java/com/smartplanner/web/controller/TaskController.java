package com.smartplanner.web.controller;

import com.smartplanner.web.model.Task;
import com.smartplanner.web.model.Priority;
import com.smartplanner.web.model.User;
import com.smartplanner.web.service.TaskService;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * ============================================================
 * CLASS: TaskController
 * ============================================================
 * PURPOSE
 * -------
 * Handles HTTP requests for task operations.
 * This allows users to interact with the system via browser.
 * *
 * ============================================================
 */

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService = new TaskService();
    /*
     * ============================================================
     * ENDPOINT: ADD TASK
     * ============================================================
     * URL:
     * http://localhost:8080/tasks/add
     */
    @GetMapping("/add")
    public String addTask(
            @RequestParam String username,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String dueDate,
            @RequestParam String priority
    ) {

        User user = new User(username, ""); // password not needed here

        Task task = new Task(
                title,
                description,
                LocalDate.parse(dueDate),
                Priority.valueOf(priority.toUpperCase())
        );

        taskService.addTask(user, task);

        return "Task added successfully!";
    }

    /*
     * ============================================================
     * ENDPOINT: VIEW TASKS
     * ============================================================
     * URL:
     * http://localhost:8080/tasks/view
     */
    @GetMapping("/view")
    public List<Task> viewTasks(@RequestParam String username) {
        User user = new User(username, "");
        return taskService.getTasks(user);
    }
}