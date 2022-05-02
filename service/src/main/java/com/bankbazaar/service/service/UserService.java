package com.bankbazaar.service.service;

import com.bankbazaar.core.manager.UserManager;
import com.bankbazaar.core.model.UserEntity;
import com.bankbazaar.dto.model.UserDto;
import com.bankbazaar.service.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserMapper modelMapper;
    @Autowired
    private UserManager manager;


    public UserDto addUser(UserDto user){
        UserEntity response = manager.insertUser(modelMapper.dtoToDomain(user));
        return  modelMapper.domainToDto(response);
    }

    public UserDto updateUser(UserDto user) {
        UserDto data = getUserDetails(user.getEmail());
        user.setUserId(data.getUserId());
        UserEntity response = manager.updateUser(modelMapper.dtoToDomain(user));
        return  modelMapper.domainToDto(response);

    }

    public UserDto getUserDetails(String email) {
        Optional<UserEntity> response = manager.getUserByEmail(email);
        return response.map(entity -> modelMapper.domainToDto(entity)).orElse(null);
    }
    public UserDto loggedInUserDetails(Long userId)
    {
        Optional<UserEntity> response = manager.getUserById(userId);
        return response.map(entity -> modelMapper.domainToDto(entity)).orElse(null);
    }
}
