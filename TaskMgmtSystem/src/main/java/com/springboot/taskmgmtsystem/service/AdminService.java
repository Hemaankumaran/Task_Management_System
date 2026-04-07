package com.springboot.taskmgmtsystem.service;

import com.springboot.taskmgmtsystem.dto.AdminReqDto;
import com.springboot.taskmgmtsystem.enums.Role;
import com.springboot.taskmgmtsystem.model.User;
import com.springboot.taskmgmtsystem.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    public void addAdmin(AdminReqDto adminReqDto) {
        User user = new User();
        user.setUsername(adminReqDto.username());
        user.setPassword(passwordEncoder.encode(adminReqDto.password()));
        user.setRole(Role.ADMIN);
        userRepo.save(user);
    }
}
