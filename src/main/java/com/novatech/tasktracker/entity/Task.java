package com.novatech.tasktracker.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    Long id;

    String title;
    String description;
    @Enumerated(EnumType.STRING)
    TaskStatus status;

    LocalDateTime dateCreated;
    LocalDateTime dateDeadline;
}
