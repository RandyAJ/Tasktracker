package com.novatech.tasktracker.dto;

import com.novatech.tasktracker.entity.TaskStatus;

public record CreateTaskRequest(
        String title
) {}

