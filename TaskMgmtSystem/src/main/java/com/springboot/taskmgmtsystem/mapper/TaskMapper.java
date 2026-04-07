package com.springboot.taskmgmtsystem.mapper;

import com.springboot.taskmgmtsystem.dto.TaskResDto;
import com.springboot.taskmgmtsystem.model.Task;

public class TaskMapper {

    public static TaskResDto toDto(Task task){
        return new TaskResDto(
                task.getId(),
                task.getTitle(),
                task.getDueDate(),
                task.getTaskPriority(),
                task.getTaskStatus(),
                task.getCustomer().getId(),
                task.getCustomer().getName()
        );
    }
}
