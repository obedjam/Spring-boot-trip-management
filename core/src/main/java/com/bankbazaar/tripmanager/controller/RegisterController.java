package com.bankbazaar.tripmanager.controller;


import com.bankbazaar.tripmanager.manager.RegisterManager;
import com.bankbazaar.tripmanager.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired()
    private RegisterManager service;

    @RequestMapping(method = RequestMethod.GET)
    public String welcome() throws Exception {
        return "register.html";
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Users> addUsers(@Valid @ModelAttribute Users user) {

        Users response = service.saveUsers(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
