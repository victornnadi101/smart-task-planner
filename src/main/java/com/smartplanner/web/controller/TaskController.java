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
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // ADD TASK
    @PostMapping("/add")
    public String addTask(@RequestBody TaskRequest request) {
        System.out.println(">>> ADD endpoint hit");

        User user = new User(request.getUsername(), "");

        Task task = new Task(
                request.getTitle(),
                request.getDescription(),
                LocalDate.parse(request.getDueDate()),
                Priority.valueOf(request.getPriority().toUpperCase())
        );
        taskService.addTask(user, task);
        return "Task added successfully!";
    }

    // VIEW TASKS
    @GetMapping("/view")
    public List<Task> viewTasks(@RequestParam String username) {
        System.out.println(">>> VIEW endpoint hit");

        User user = new User(username, "");
        return taskService.getTasks(user);
    }
}