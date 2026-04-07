package com.springboot.taskmgmtsystem.repository;

import com.springboot.taskmgmtsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
