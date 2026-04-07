package com.springboot.taskmgmtsystem.dto;

import com.springboot.taskmgmtsystem.enums.TaskPriority;
import com.springboot.taskmgmtsystem.enums.TaskStatus;

import java.time.LocalDate;

public record TaskResDto(
        long id,
        String title,
        LocalDate dueDate,
        TaskPriority taskPriority,
        TaskStatus taskStatus,
        Long customerId,
        String customerName
) {
}
