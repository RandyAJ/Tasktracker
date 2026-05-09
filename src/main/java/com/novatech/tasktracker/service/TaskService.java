package com.novatech.tasktracker.service;

import com.novatech.tasktracker.dto.TaskResponse;
import com.novatech.tasktracker.dto.UpdateTaskRequest;
import com.novatech.tasktracker.entity.TaskStatus;
import com.novatech.tasktracker.mapper.TaskMapper;
import org.springframework.stereotype.Service;
import com.novatech.tasktracker.repository.TaskRepository;
import com.novatech.tasktracker.entity.Task;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    private Task getEntity(Long id){
        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Task c ID %d не найдена", id)));
    }

    public TaskResponse get(Long id){
        if (id == null) {
            throw new IllegalArgumentException("ID при поиске Task не может быть null");
        }

        return taskMapper.toResponse(getEntity(id));
    }

    public TaskResponse create(String title){
        Task task = new Task();
        task.setTitle(title);

        return taskMapper.toResponse(taskRepository.save(task));
    }

    public TaskResponse update(Long id, UpdateTaskRequest request){
        Task task = getEntity(id);

        if(request.title() != null){
            task.setTitle(request.title());
        }
        if(request.description() != null){
            task.setDescription(request.description());
        }

        if(request.status() != null){
            task.setStatus(request.status());
        }

        if(request.dateDeadline() != null){
            task.setDateDeadline(request.dateDeadline());
        }

        return taskMapper.toResponse(taskRepository.save(task));
    }

    public TaskResponse updateStatus(Long id, TaskStatus status){
        Task task = getEntity(id);

        task.setStatus(status);

        return taskMapper.toResponse(taskRepository.save(task));
    }

    public void delete(Long id){
        getEntity(id);
        taskRepository.deleteById(id);
    }

    public List<Task> listByStatus(TaskStatus status){
        return taskRepository.findAllByStatus(status);
    }

    public List<Task> list(){
        return taskRepository.findAll();
    }
}
