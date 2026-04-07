package com.springboot.taskmgmtsystem.controller;

import com.springboot.taskmgmtsystem.dto.TaskPageResDto;
import com.springboot.taskmgmtsystem.dto.TaskReqDto;
import com.springboot.taskmgmtsystem.dto.TaskResDto;
import com.springboot.taskmgmtsystem.enums.TaskStatus;
import com.springboot.taskmgmtsystem.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/task")
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;

    @GetMapping("/getall") // ADMIN
    public TaskPageResDto getAllTasks(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                      @RequestParam(value = "size", required = false, defaultValue = "5") int size){
        return taskService.getAllTasks(page, size);
    }

    @GetMapping("/get/{taskId}") // authenticated
    public TaskResDto getTaskById(@PathVariable Long taskId){
        return taskService.getTaskById(taskId);
    }

    @PostMapping("/add/{customerId}") // authenticated
    public ResponseEntity<Map<String, Object>> addTask(@RequestBody TaskReqDto taskReqDto,
                                                       @PathVariable Long customerId){
        taskService.addTask(taskReqDto, customerId);
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Task added successfully..!");
        return ResponseEntity
                .status(HttpStatus.CREATED).body(map);
    }

    @PutMapping("/update/{taskId}") // authenticated
    public ResponseEntity<Map<String, Object>> updateTaskStatus(@RequestParam(value = "taskStatus") TaskStatus taskStatus,
                                                                @PathVariable Long taskId,
                                                                Principal principal){
        taskService.updateTaskStatus(taskStatus, taskId, principal.getName());
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Task updated successfully..!");
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).body(map);
    }

    @DeleteMapping("/delete/{taskId}") // authenticated
    public ResponseEntity<Map<String, Object>> deleteTaskById(@PathVariable Long taskId,
                                                              Principal principal){
        taskService.deleteTaskById(taskId, principal.getName());
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Task deleted successfully..!");
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).body(map);
    }
}
