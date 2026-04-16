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
    public ApiResponse addTask(@RequestBody TaskRequest request) {
        System.out.println(">>> ADD endpoint hit");

        User user = new User(request.getUsername(), "");

        Task task = new Task(
                request.getTitle(),
                request.getDescription(),
                LocalDate.parse(request.getDueDate()),
                Priority.valueOf(request.getPriority().toUpperCase())
        );
        taskService.addTask(user, task);
        return new ApiResponse("Task added successfully", true);
    }

    // VIEW TASKS
    @GetMapping("/view")
    public List<Task> viewTasks(@RequestParam String username) {
        System.out.println(">>> VIEW endpoint hit");

        User user = new User(username, "");
        return taskService.getTasks(user);
    }

    @GetMapping("/complete")
    public ApiResponse completeTask(@RequestParam int id) {
        taskService.completeTask(id);
        return new ApiResponse("Task completed successfully", true);
    }

    @GetMapping("/delete")
    public ApiResponse deleteTask(@RequestParam int id) {
        taskService.deleteTask(id);
        return new ApiResponse("Task deleted successfully", true);
    }
}