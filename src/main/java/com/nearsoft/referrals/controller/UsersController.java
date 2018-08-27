package com.nearsoft.referrals.controller;

import com.nearsoft.referrals.model.ReferredByUser;
import com.nearsoft.referrals.model.User;
import com.nearsoft.referrals.repository.ReferredByUserRepository;
import com.nearsoft.referrals.service.UserService;
import org.slf4j.LoggerFactory;
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
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    public UsersController(UserService userService, ReferredByUserRepository referredByUserRepository) {
        this.userService = userService;
        this.referredByUserRepository = referredByUserRepository;
    }

    @RequestMapping(value = "/user")
    String getUser(Principal principal) {
        LOGGER.trace("Retrieving the user email for the principal: {}", principal);
        String user = principal.getName();
        return user;
    }

    @RequestMapping(value = "/referreds")
    ResponseEntity<List<ReferredByUser>> referreds(Principal principal) {
        User user = userService.getPrincipalUser(principal.getName());
        LOGGER.trace("Retrieving the referreds for the user: {}", user);
        List<ReferredByUser> referreds = referredByUserRepository.findByReferUser(user.getId());
        LOGGER.trace("Referreds retrieved: {}", referreds);
        return new ResponseEntity<>(referreds, HttpStatus.OK);
    }

}
