package com.Nearsoft.referrals.controller;

import com.Nearsoft.referrals.service.GoogleTokenVerifyService;
import com.Nearsoft.referrals.service.TokenGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;


@RestController
public class GoogleLoginController {
    private GoogleTokenVerifyService googleTokenVerifyService;
    @Autowired
    private TokenGeneratorService tokenGeneratorService;

    public GoogleLoginController(GoogleTokenVerifyService googleTokenVerifyService) {
        this.googleTokenVerifyService = googleTokenVerifyService;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity login( @RequestParam("token_id") String tokenId) throws GeneralSecurityException, IOException {
        if(googleTokenVerifyService.verifyToken(tokenId))
        {
            tokenGeneratorService.generateToken();
            return new ResponseEntity(HttpStatus.OK);
        }

        else
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }
}
