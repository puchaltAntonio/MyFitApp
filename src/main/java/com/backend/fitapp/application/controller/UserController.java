package com.backend.fitapp.application.controller;

import com.backend.fitapp.domain.model.User;
import com.backend.fitapp.domain.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
    @Autowired
    private UserServiceImpl service;
    @PostMapping(path = "/add")
    public @ResponseBody ResponseEntity<User> addNewUser (@RequestBody User user) {
        User newUser = service.saveUser(user);

        if(newUser != null){
            return new ResponseEntity<User>(newUser, HttpStatus.OK);
        }

        return new ResponseEntity<User>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @GetMapping(path = "get/{username}")
    public @ResponseBody ResponseEntity<User> getUserByUsername(@PathVariable String username){
        User userFound = service.findUserByUsername(username);
        if(userFound != null) {
            return new ResponseEntity<>(userFound, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}