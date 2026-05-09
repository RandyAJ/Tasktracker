package com.novatech.tasktracker.controller;

import com.novatech.tasktracker.entity.Task;
import com.novatech.tasktracker.entity.TaskStatus;
import com.novatech.tasktracker.service.TaskService;
import com.novatech.tasktracker.dto.CreateTaskRequest;
import com.novatech.tasktracker.dto.UpdateTaskRequest;
import com.novatech.tasktracker.dto.TaskResponse;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public TaskResponse get(@PathVariable Long id) {
        return taskService.get(id);
    }

    @PostMapping
    public TaskResponse create(@RequestBody CreateTaskRequest request) {
        return taskService.create(request.title());
    }

    @PatchMapping("/{id}")
    public TaskResponse update(@PathVariable Long id,
                       @RequestBody UpdateTaskRequest request) {
        return taskService.update(id, request); // переделать на update всего объекта
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.delete(id); // return true or false
    }

    /**/

    @GetMapping("/listByStatus/{status}")
    public List<Task> listByStatus(@PathVariable TaskStatus status) {
        return taskService.listByStatus(status);
    }

    @GetMapping("/list")
    public List<Task> list() {
        return taskService.list();
    }
}
