package com.novatech.tasktracker.service;

import com.novatech.tasktracker.entity.TaskStatus;
import org.springframework.stereotype.Service;
import com.novatech.tasktracker.repository.TaskRepository;
import com.novatech.tasktracker.entity.Task;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(String title){
        Task task = new Task();
        task.setTitle(title);

        return taskRepository.save(task);
    }

    public Task get(Long id){
        if (id == null) {
            throw new IllegalArgumentException("ID при поиске Task не может быть null");
        }

        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Task with id %d not found", id)));
    }

    public Task updateStatus(Long id, TaskStatus status){
        Task task = this.get(id);
        task.setStatus(status);

        return taskRepository.save(task);
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}
