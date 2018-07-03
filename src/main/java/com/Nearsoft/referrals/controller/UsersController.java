package com.Nearsoft.referrals.controller;

import com.Nearsoft.referrals.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/users/register", method = RequestMethod.POST)
    public ResponseEntity registerUser(@RequestParam("email") String email, @RequestParam("name") String name) {
        userService.registerUser(email, name);
        return new ResponseEntity(HttpStatus.CREATED);
    }


}
