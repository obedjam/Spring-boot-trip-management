package com.bankbazaar.service.controller;
import com.bankbazaar.dto.model.UserDto;
import com.bankbazaar.service.service.UserService;
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
        String plainPassword = user.getPassword();
        UserDto response = userService.addUser(user, request);
        if(response==null)
        {
            return "redirect:/user/register?status=1";
        }
        userService.authWithHttpServletRequest(request,user.getEmail(),plainPassword);
        return "redirect:/user";
    }

    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public String updateUserPage(@RequestParam(value = "status", defaultValue = "0") Integer status){
        return "update_account";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateUserData( @ModelAttribute UserDto user, Principal principal) {
        UserDto response = userService.updateUser(user, principal);
        if(response==null)
        {
            return "redirect:/user/update?status=1";
        }
        return "redirect:/user";
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getUserDetails(Principal principal) {
        UserDto response = userService.getUserDetails(principal);
        ModelAndView user;
        if (response==null) {
            user = new ModelAndView("logout");
        }
        else {
            user = new ModelAndView("user_account");
            user.addObject("userData", response);
        }
        return user;
    }



}
