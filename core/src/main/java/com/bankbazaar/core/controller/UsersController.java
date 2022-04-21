package com.bankbazaar.core.controller;

import com.bankbazaar.core.manager.UsersManager;
import com.bankbazaar.core.model.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UsersManager service;

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String welcome(@RequestParam(value = "status", defaultValue = "0") Integer status){
        return "register";
    }

    @RequestMapping(value= "/register",method = RequestMethod.POST)
    public String addUsers(@Valid @ModelAttribute Users user, HttpServletRequest request) throws Exception{
        String plainPassword = user.getPassword();
        Users response = service.insertUsers(user);
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
    public String addUsers(@Valid @ModelAttribute Users user, Principal principal) {
        Users data = service.getUsersById(principal.getName()).get();
        user.setUserId(data.getUserId());
        Users response = service.updateUsers(user);
        if(response==null)
        {
            return "redirect:/user/update?status=1";
        }
        return "redirect:/user";
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getUserDetails(Principal principal) {
        Optional<Users> userData = service.getUsersById(principal.getName());
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

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Users> deleteUsers(@RequestParam Long id) {

        if (!service.deleteUsers(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            log.error("Error while login",e);
        }
    }
}
