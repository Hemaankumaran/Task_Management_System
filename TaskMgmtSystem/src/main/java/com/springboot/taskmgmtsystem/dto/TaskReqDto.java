package com.springboot.taskmgmtsystem.dto;

import com.springboot.taskmgmtsystem.enums.TaskPriority;
import com.springboot.taskmgmtsystem.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TaskReqDto(
        @NotNull
        @NotBlank
        String title,
        @NotNull
        @NotBlank
        String description,
        @NotNull
        LocalDate dueDate,
        @NotNull
        TaskPriority taskPriority,
        @NotNull
        TaskStatus taskStatus
) {
}
