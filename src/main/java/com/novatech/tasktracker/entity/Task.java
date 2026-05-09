package com.novatech.tasktracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.AssertTrue;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // ID генерируется до Insert в БД, можно использовать пакетную вставку (batching)
    private Long id;

    @NotNull // на уровне кода до сэйва
    @Setter
    private String title;

    @Setter
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false) // на уровне БД потому что задаю пресейвом
    @Setter
    private TaskStatus status;

    @Column(nullable = false)
    private LocalDateTime dateCreated;

    @Setter
    private LocalDateTime dateDeadline;

    /**/

    @PrePersist
    protected void onCreate() {
        if(this.dateCreated == null){
            this.dateCreated = LocalDateTime.now();
            System.out.println("Сущность будет сохранена с датой: " + this.dateCreated); // логировать в сервисе.
        }

        if(this.status == null){
            this.status = TaskStatus.BACKLOG;
            System.out.println("Сущность будет сохранена со статусом: " + this.status); // логировать в сервисе.
        }
    }

    @AssertTrue
    public boolean isDeadlineValid() {
        return dateDeadline == null || !dateDeadline.isBefore(dateCreated); // дата создания должна быть раньше дедлайна. либо дедлайн может быть пустым
    }
}
