package com.nearsoft.referrals.controller;

import com.nearsoft.referrals.model.ReferredByUser;
import com.nearsoft.referrals.repository.ReferredByUserRepository;
import com.nearsoft.referrals.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class UsersController {

    private UserService userService;

    private ReferredByUserRepository referredByUserRepository;

    public UsersController(UserService userService, ReferredByUserRepository referredByUserRepository) {
        this.userService = userService;
        this.referredByUserRepository = referredByUserRepository;
    }

    @RequestMapping(value = "/user")
    String getUser(Principal principal) {
        return principal.getName();
    }

    @RequestMapping(value = "/referreds")
    ResponseEntity<List<ReferredByUser>> referreds(Principal principal) {

        return new ResponseEntity<>(referredByUserRepository.findByReferUser(principal.getName()), HttpStatus.OK);
    }

}
