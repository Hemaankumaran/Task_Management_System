package com.springboot.taskmgmtsystem.mapper;

import com.springboot.taskmgmtsystem.dto.CustomerSignUpDto;
import com.springboot.taskmgmtsystem.model.Customer;

public class CustomerMapper {
    public static Customer toEntity(CustomerSignUpDto customerSignUpDto){
        Customer customer = new Customer();
        customer.setName(customerSignUpDto.customer_name());
        customer.setContact(customerSignUpDto.contact());
        return customer;
    }
}
