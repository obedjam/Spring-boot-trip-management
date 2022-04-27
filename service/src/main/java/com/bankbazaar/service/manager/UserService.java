package com.bankbazaar.service.manager;

import com.bankbazaar.core.manager.UserManager;
import com.bankbazaar.core.model.UserEntity;
import com.bankbazaar.dto.model.UserDto;
import com.bankbazaar.service.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserManager userManager;
    @Autowired
    private UserMapper modelMapper;
    @Autowired
    private UserManager manager;

    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            log.error("Error while login",e);
        }
    }

    public Optional<UserEntity> userDetails(Principal principal)
    {
        return userManager.getUserByEmail(principal.getName());
    }

    public Optional<UserEntity> getUserDetails(Long userId)
    {
        return userManager.getUserById(userId);
    }

    public UserDto addUser(UserDto user, HttpServletRequest request){
        UserEntity response = manager.insertUser(modelMapper.dtoToDomain(user));
        return  modelMapper.domainToDto(response);
    }

    public UserDto updateUser(UserDto user, Principal principal) {
        Optional<UserEntity> data = userDetails(principal);
        user.setUserId(data.get().getUserId());
        UserEntity response = manager.updateUser(modelMapper.dtoToDomain(user));
        return  modelMapper.domainToDto(response);

    }

    public UserDto getUserDetails(Principal principal) {
        UserEntity response = manager.getUserByEmail(principal.getName()).get();
        return modelMapper.domainToDto(response);
    }
}
