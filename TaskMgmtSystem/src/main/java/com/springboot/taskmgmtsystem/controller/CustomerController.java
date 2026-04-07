package com.springboot.taskmgmtsystem.controller;

import com.springboot.taskmgmtsystem.dto.CustomerSignUpDto;
import com.springboot.taskmgmtsystem.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/signup") // PermitAll
    public ResponseEntity<Map<String, Object>> addCustomerSignUp(@Valid @RequestBody CustomerSignUpDto customerSignUpDto){
        customerService.addCustomerSignUp(customerSignUpDto);
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Welcome!!");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(map);
    }
}
