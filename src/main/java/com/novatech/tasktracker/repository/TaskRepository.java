package com.novatech.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.novatech.tasktracker.entity.Task;
import java.util.List;
import com.novatech.tasktracker.entity.TaskStatus;
import java.time.LocalDateTime;

public interface TaskRepository extends JpaRepository<Task, Long>{
    List<Task> findAllByStatus(TaskStatus status);

    List<Task> findAllByTitle(String title);

    List<Task> findAllByDateDeadlineBefore(LocalDateTime date);
}