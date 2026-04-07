package com.springboot.taskmgmtsystem.service;

import com.springboot.taskmgmtsystem.dto.CustomerSignUpDto;
import com.springboot.taskmgmtsystem.enums.Role;
import com.springboot.taskmgmtsystem.exceptions.ResourceNotFoundException;
import com.springboot.taskmgmtsystem.mapper.CustomerMapper;
import com.springboot.taskmgmtsystem.mapper.UserMapper;
import com.springboot.taskmgmtsystem.model.Customer;
import com.springboot.taskmgmtsystem.model.User;
import com.springboot.taskmgmtsystem.repository.CustomerRepo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepo customerRepo;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    public Customer getById(long customerId) {
        return customerRepo.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid customer id.."));
    }

    public void addCustomerSignUp(@Valid CustomerSignUpDto customerSignUpDto) {
        // get entities of Customer & User
        Customer customer = CustomerMapper.toEntity(customerSignUpDto);
        User user = UserMapper.toEntity(customerSignUpDto);

        // password encryption
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // add missing fields of user and save user in db
        user.setRole(Role.CUSTOMER);
        user = userService.addUser(user);

        // inject user into customer
        customer.setUser(user);

        // save
        customerRepo.save(customer);
    }
}
