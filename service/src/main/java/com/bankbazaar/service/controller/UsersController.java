package com.bankbazaar.service.controller;
import com.bankbazaar.dto.model.UserDto;
import com.bankbazaar.service.manager.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(@RequestParam(value = "status", defaultValue = "0") Integer status){
        return "register";
    }

    @RequestMapping(value= "/register",method = RequestMethod.POST)
    public String addUser( @ModelAttribute UserDto user, HttpServletRequest request){
        return userService.addUserService(user, request);
    }

    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public String update(@RequestParam(value = "status", defaultValue = "0") Integer status){
        return "update_account";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateService( @ModelAttribute UserDto user, Principal principal) {
        return userService.updateUserService(user, principal);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getUserDetails(Principal principal) {
        return  userService.getUserDetailsService(principal);

    }



}
