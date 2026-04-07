package com.springboot.taskmgmtsystem.mapper;

import com.springboot.taskmgmtsystem.dto.CustomerSignUpDto;
import com.springboot.taskmgmtsystem.model.User;

public class UserMapper {
    public static User toEntity(CustomerSignUpDto customerSignUpDto){
        User user = new User();
        user.setUsername(customerSignUpDto.username());
        user.setPassword(customerSignUpDto.password());
        return user;
    }
}
