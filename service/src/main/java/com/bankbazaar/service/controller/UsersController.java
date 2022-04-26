package com.bankbazaar.service.controller;

import com.bankbazaar.core.manager.UserEntityManager;
import com.bankbazaar.core.model.UserEntity;
import com.bankbazaar.dto.model.UserDto;
import com.bankbazaar.service.manager.DataManager;
import com.bankbazaar.service.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UserEntityManager manager;
    @Autowired
    private DataManager service;
    @Autowired
    private UserMapper modelMapper;
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(@RequestParam(value = "status", defaultValue = "0") Integer status){
        return "register";
    }

    @RequestMapping(value= "/register",method = RequestMethod.POST)
    public String addUsers( @ModelAttribute UserDto user, HttpServletRequest request) throws Exception{
        String plainPassword = user.getPassword();
        UserEntity response = manager.insertUsers(modelMapper.dtoToDomain(user));
        if(response==null)
        {
            return "redirect:/user/register?status=1";
        }
        authWithHttpServletRequest(request,user.getEmail(),plainPassword);
        return "redirect:/user";
    }

    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public String update(@RequestParam(value = "status", defaultValue = "0") Integer status){
        return "update_account";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addUsers( @ModelAttribute UserDto user, Principal principal) {
        Optional<UserEntity> data = service.userDetails(principal);
        user.setUserId(data.get().getUserId());
        UserEntity response = manager.updateUsers(modelMapper.dtoToDomain(user));
        if(response==null)
        {
            return "redirect:/user/update?status=1";
        }
        return "redirect:/user";
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getUserDetails(Principal principal) {
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


    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            log.error("Error while login",e);
        }
    }
}
