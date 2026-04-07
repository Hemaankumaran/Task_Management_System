package com.springboot.taskmgmtsystem.controller;

import com.springboot.taskmgmtsystem.dto.AdminReqDto;
import com.springboot.taskmgmtsystem.service.AdminService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/add") // permitAll
    public void addAdmin(@Valid @RequestBody AdminReqDto adminReqDto){
        adminService.addAdmin(adminReqDto);
    }
}
