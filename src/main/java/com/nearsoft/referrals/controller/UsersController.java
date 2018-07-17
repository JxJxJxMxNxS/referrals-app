package com.nearsoft.referrals.controller;

import com.nearsoft.referrals.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }


    @RequestMapping(value = "/users/register", method = RequestMethod.POST)
    public ResponseEntity registerUser(@RequestParam("email") String email, @RequestParam("name") String name) {
        usersService.registerUser(email, name);
        return new ResponseEntity(HttpStatus.CREATED);
    }


}
