package com.springboot.taskmgmtsystem.service;

import com.springboot.taskmgmtsystem.exceptions.ResourceNotFoundException;
import com.springboot.taskmgmtsystem.model.Customer;
import com.springboot.taskmgmtsystem.repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepo customerRepo;

    public Customer getById(long customerId) {
        return customerRepo.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid customer id.."));
    }
}
