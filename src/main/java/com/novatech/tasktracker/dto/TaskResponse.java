package com.novatech.tasktracker.dto;

import com.novatech.tasktracker.entity.TaskStatus;
import java.time.LocalDateTime;

public record TaskResponse (
        Long id,
        String title,
        String description,
        TaskStatus status,
        LocalDateTime dateCreated,
        LocalDateTime dateDeadline
) {}