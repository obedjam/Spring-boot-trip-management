package com.bankbazaar.tripmanager.controller;

import com.bankbazaar.tripmanager.manager.UsersManager;
import com.bankbazaar.tripmanager.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UsersManager service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Users> addUsers(@Valid @RequestBody Users user) {

        Users response = service.saveUsers(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value="/user-account",method = RequestMethod.GET)
    public ResponseEntity<Optional<Users>> findUsersById(@RequestParam Long id) {

        Optional<Users> userData = service.getUsersById(id);
        if (userData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userData, HttpStatus.OK);
    }

    @RequestMapping(value="/delete-user",method = RequestMethod.DELETE)
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
