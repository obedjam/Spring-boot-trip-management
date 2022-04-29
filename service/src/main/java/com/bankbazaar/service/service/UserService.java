package com.bankbazaar.service.service;

import com.bankbazaar.core.manager.UserManager;
import com.bankbazaar.core.model.UserEntity;
import com.bankbazaar.core.security.LoginUserDetails;
import com.bankbazaar.dto.model.UserDto;
import com.bankbazaar.service.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

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
        UserEntity response = manager.getUserByEmail(email).get();
        return modelMapper.domainToDto(response);
    }
    public UserDto loggedInUserDetails(Long userId)
    {
        UserEntity response = manager.getUserById(userId).get();
        return modelMapper.domainToDto(response);
    }
}
