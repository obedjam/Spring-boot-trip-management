package com.bankbazaar.tripmanager.controller;


import com.bankbazaar.tripmanager.manager.RegisterManager;
import com.bankbazaar.tripmanager.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired()
    private RegisterManager service;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @RequestMapping(method = RequestMethod.GET)
    public String welcome(){
        return "register";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String addUsers(@Valid @ModelAttribute Users user, HttpServletRequest request) {
        String plainPassword = user.getPassword();
        Users response = service.saveUsers(user);
        if(response==null)
        {
            return "email_already_registered";
        }
        authWithHttpServletRequest(request,user.getEmail(),plainPassword);
        return "redirect:/user";
    }

    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            System.out.println(username);
            System.out.println(password);
            request.login(username, password);
        } catch (ServletException e) {
            LOGGER.error("Error while login ", e);
        }
    }
}
