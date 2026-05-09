package com.novatech.tasktracker.mapper;

import org.springframework.stereotype.Component;
import com.novatech.tasktracker.dto.TaskResponse;
import com.novatech.tasktracker.entity.Task;

@Component
public class TaskMapper {
    public TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getDateCreated(),
                task.getDateDeadline()
        );
    }
}
