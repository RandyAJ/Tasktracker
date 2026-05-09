package com.novatech.tasktracker.dto;

import com.novatech.tasktracker.entity.TaskStatus;
import java.time.LocalDateTime;

public record UpdateTaskRequest (
        String title,
        String description,
        TaskStatus status,
        LocalDateTime dateDeadline
) {}