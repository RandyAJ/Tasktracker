package com.novatech.tasktracker.service;

import com.novatech.tasktracker.entity.TaskStatus;
import org.springframework.stereotype.Service;
import com.novatech.tasktracker.repository.TaskRepository;
import com.novatech.tasktracker.entity.Task;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(String title){
        Task task = new Task();
        task.setTitle(title);

        return taskRepository.save(task);
    }

    public Task get(Long id){
        if (id == null) {
            throw new IllegalArgumentException("ID при поиске Task не может быть null");
        }

        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Task c ID %d не найдена", id)));
    }

    public Task updateStatus(Long id, TaskStatus status){
        Task task = this.get(id);
        task.setStatus(status);

        return taskRepository.save(task);
    }

    public void delete(Long id){
        taskRepository.deleteById(id);
    }

    public List<Task> listByStatus(TaskStatus status){
        return taskRepository.findAllByStatus(status);
    }
}
