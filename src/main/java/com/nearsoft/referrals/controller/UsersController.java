package com.nearsoft.referrals.controller;

import com.nearsoft.referrals.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UsersController {

    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/user")
    String getUser(Principal principal) {
        return principal.getName();
    }

}
