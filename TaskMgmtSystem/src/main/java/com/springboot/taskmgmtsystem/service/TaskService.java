package com.springboot.taskmgmtsystem.service;

import com.springboot.taskmgmtsystem.dto.TaskReqDto;
import com.springboot.taskmgmtsystem.dto.TaskResDto;
import com.springboot.taskmgmtsystem.enums.TaskStatus;
import com.springboot.taskmgmtsystem.exceptions.ResourceNotFoundException;
import com.springboot.taskmgmtsystem.mapper.TaskMapper;
import com.springboot.taskmgmtsystem.model.Customer;
import com.springboot.taskmgmtsystem.model.Task;
import com.springboot.taskmgmtsystem.repository.TaskRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepo taskRepo;
    private CustomerService customerService;

    public List<TaskResDto> getAllTasks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Task> taskPage = taskRepo.findAll(pageable);
        return taskPage.stream()
                .map(TaskMapper :: toDto)
                .toList();
    }

    public TaskResDto getTaskById(Long taskId) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid task id.."));
        return TaskMapper.toDto(task);
    }

    public void addTask(TaskReqDto taskReqDto, long customerId) {
        Task task = new Task();
        task.setTitle(taskReqDto.title());
        task.setDescription(taskReqDto.description());
        task.setDueDate(taskReqDto.dueDate());
        task.setTaskPriority(taskReqDto.taskPriority());
        task.setTaskStatus(taskReqDto.taskStatus());

        Customer customer = customerService.getById(customerId);

        task.setCustomer(customer);

        taskRepo.save(task);
    }

    public void updateTaskStatus(TaskStatus taskStatus, Long taskId, String name) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid task id.."));

        // check permission

        task.setTaskStatus(taskStatus);

        taskRepo.save(task);
    }

    public void deleteTaskById(Long taskId, String name) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid task id.."));

        //check permission

        taskRepo.deleteById(taskId);
    }
}
