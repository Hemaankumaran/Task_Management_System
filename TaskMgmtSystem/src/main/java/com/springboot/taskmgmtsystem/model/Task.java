package com.springboot.taskmgmtsystem.model;

import com.springboot.taskmgmtsystem.enums.TaskPriority;
import com.springboot.taskmgmtsystem.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotBlank
    private String title;
    private String description;
    @NotNull
    @Column(name = "due_date")
    private LocalDate dueDate;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "task_priority")
    private TaskPriority taskPriority;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "task_status")
    private TaskStatus taskStatus;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @ManyToOne
    private Customer customer;
}
