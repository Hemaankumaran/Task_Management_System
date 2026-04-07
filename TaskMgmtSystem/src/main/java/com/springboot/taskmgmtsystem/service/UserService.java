package com.springboot.taskmgmtsystem.service;

import com.springboot.taskmgmtsystem.model.User;
import com.springboot.taskmgmtsystem.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.loadUserByUsername(username);
    }

    public User addUser(User user) {
        return userRepo.save(user);
    }
}
