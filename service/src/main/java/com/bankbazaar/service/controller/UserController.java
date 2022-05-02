package com.bankbazaar.service.controller;
import com.bankbazaar.core.security.LoginUserDetails;
import com.bankbazaar.dto.model.UserDto;
import com.bankbazaar.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(@RequestParam(value = "status", defaultValue = "0") Integer status){
        return "register";
    }

    @RequestMapping(value= "/register",method = RequestMethod.POST)
    public String addUser( @ModelAttribute UserDto user, HttpServletRequest request){
        String plainPassword = user.getPassword();
        UserDto response = userService.addUser(user);
        if(response==null)
        {
            return "redirect:/user/register?status=1";
        }
        authWithHttpServletRequest(request,user.getEmail(),plainPassword);
        return "redirect:/user";
    }

    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public String updateUserPage(@RequestParam(value = "status", defaultValue = "0") Integer status){
        return "update_account";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateUserData( @ModelAttribute UserDto user) {
        UserDto response = userService.updateUser(user);
        if(response==null)
        {
            return "redirect:/user/update?status=1";
        }
        return "redirect:/user";
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getUserDetails(@AuthenticationPrincipal LoginUserDetails loginUserDetails) {
        UserDto response = userService.loggedInUserDetails(loginUserDetails.getUserId());
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

    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            log.error("Error while login",e);
        }
    }

}
