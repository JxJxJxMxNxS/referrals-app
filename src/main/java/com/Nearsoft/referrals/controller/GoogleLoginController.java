package com.Nearsoft.referrals.controller;

import com.Nearsoft.referrals.service.GoogleTokenVerifyService;
import com.Nearsoft.referrals.service.TokenGeneratorService;
import com.google.api.client.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    public String login(@RequestParam("token_id") String tokenId) throws GeneralSecurityException, IOException {
        if(googleTokenVerifyService.verifyToken(tokenId))
        {
            return tokenGeneratorService.generateToken();
        }
        else
            return "";
    }
}
