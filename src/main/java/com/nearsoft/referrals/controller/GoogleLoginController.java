package com.nearsoft.referrals.controller;

import com.nearsoft.referrals.model.User;
import com.nearsoft.referrals.repository.UserRepository;
import com.nearsoft.referrals.service.GoogleUserService;
import com.nearsoft.referrals.service.TokenGeneratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;


@RestController
public class GoogleLoginController {
    private GoogleUserService googleUserService;

    private TokenGeneratorService tokenGeneratorService;

    private UserRepository userRepository;

    public GoogleLoginController(GoogleUserService googleUserService, TokenGeneratorService tokenGeneratorService, UserRepository userRepository) {
        this.googleUserService = googleUserService;
        this.tokenGeneratorService = tokenGeneratorService;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<User> login(@RequestParam("token_id") String tokenId) throws GeneralSecurityException, IOException {
        User databaseUser;
        User user = googleUserService.verifyTokenAndCreateUser(tokenId);

        if (user != null) {
            databaseUser = userRepository.findByemail(user.getEmail());
            user = databaseUser == null ? userRepository.save(user) : databaseUser;
            user.setToken(tokenGeneratorService.generateToken());
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else
            return new ResponseEntity<>(user, HttpStatus.FORBIDDEN);
    }
}
