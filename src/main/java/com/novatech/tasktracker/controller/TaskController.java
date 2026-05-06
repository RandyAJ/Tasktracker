package com.novatech.tasktracker.controller;

import com.novatech.tasktracker.entity.Task;
import com.novatech.tasktracker.entity.TaskStatus;
import com.novatech.tasktracker.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create") // через боди в джейсоне бы принимал title
    public Task create(@RequestBody String title) {
        return taskService.create(title);
    }

    @GetMapping("/get/{id}")
    public Task get(@PathVariable Long id) {
        return taskService.get(id);
    }

    @GetMapping("/listByStatus")
    public List<Task> listByStatus(@RequestBody TaskStatus status) {
        return taskService.listByStatus(status);
    }

    @PutMapping("/update/{id}") // через юрл айди, через боди в джейсоне статус
    public Task update(@PathVariable Long id,
                       @RequestBody TaskStatus status) {
        return taskService.updateStatus(id, status);
    }

    @DeleteMapping("/delete/{id}") // через юрл айди, через боди в джейсоне статус
    public void delete(@PathVariable Long id) {
        taskService.delete(id); // return true or false
    }
}
