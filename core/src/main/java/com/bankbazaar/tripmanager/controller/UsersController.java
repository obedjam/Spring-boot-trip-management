package com.bankbazaar.tripmanager.controller;

import com.bankbazaar.tripmanager.manager.UsersManager;
import com.bankbazaar.tripmanager.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;
@Controller
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UsersManager service;

    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public String update(){
        return "update_account";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String addUsers(@Valid @ModelAttribute Users user, Principal principal) {
        Users data = service.getUsersById(principal.getName()).get();
        user.setUserId(data.getUserId());
        Users response = service.saveUsers(user);
        if(response==null)
        {
            return "email_already_registered";
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

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Users> updateUsers(@Valid @RequestBody Users user) {

        Users response = service.saveUsers(user);
        if(response == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
