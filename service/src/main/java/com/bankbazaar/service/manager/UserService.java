package com.bankbazaar.service.manager;

import com.bankbazaar.core.manager.UserManager;
import com.bankbazaar.core.model.UserEntity;
import com.bankbazaar.dto.model.UserDto;
import com.bankbazaar.service.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

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
        Optional<UserEntity> userData = userManager.getUserByEmail(principal.getName());
        return userData;
    }

    public Optional<UserEntity> getUserDetails(Long userId)
    {
        Optional<UserEntity> userData = userManager.getUserById(userId);
        return userData;
    }

    public String addUserService(UserDto user, HttpServletRequest request){
        String plainPassword = user.getPassword();
        UserEntity response = manager.insertUsers(modelMapper.dtoToDomain(user));
        if(response==null)
        {
            return "redirect:/user/register?status=1";
        }
        authWithHttpServletRequest(request,user.getEmail(),plainPassword);
        return "redirect:/user";
    }

    public String updateUserService(UserDto user, Principal principal) {
        Optional<UserEntity> data = userDetails(principal);
        user.setUserId(data.get().getUserId());
        UserEntity response = manager.updateUsers(modelMapper.dtoToDomain(user));
        if(response==null)
        {
            return "redirect:/user/update?status=1";
        }
        return "redirect:/user";
    }

    public ModelAndView getUserDetailsService(Principal principal) {
        Optional<UserEntity> userData = manager.getUserByEmail(principal.getName());
        ModelAndView user;
        if (userData.isEmpty()) {
            user = new ModelAndView("logout");
        }
        else {
            user = new ModelAndView("user_account");
            user.addObject("userData", userData.get());
        }
        return user;

    }
}
