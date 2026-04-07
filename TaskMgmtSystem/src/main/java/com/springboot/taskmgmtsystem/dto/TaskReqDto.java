package com.springboot.taskmgmtsystem.dto;

import com.springboot.taskmgmtsystem.enums.TaskPriority;
import com.springboot.taskmgmtsystem.enums.TaskStatus;

import java.time.LocalDate;

public record TaskReqDto(
        String title,
        String description,
        LocalDate dueDate,
        TaskPriority taskPriority,
        TaskStatus taskStatus
) {
}
