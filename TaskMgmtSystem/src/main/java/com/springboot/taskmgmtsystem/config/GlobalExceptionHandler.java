package com.springboot.taskmgmtsystem.config;

import com.springboot.taskmgmtsystem.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException e){
        Map<String, Object> map = new HashMap<>();
        map.put("message", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(map);
    }
}
