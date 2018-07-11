package com.nearsoft.referrals.controller;

import com.nearsoft.referrals.service.GoogleTokenVerifyService;
import com.nearsoft.referrals.service.TokenGeneratorService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;


@RestController
public class GoogleLoginController {
    private GoogleTokenVerifyService googleTokenVerifyService;

    private TokenGeneratorService tokenGeneratorService;

    public GoogleLoginController(GoogleTokenVerifyService googleTokenVerifyService, TokenGeneratorService tokenGeneratorService) {
        this.googleTokenVerifyService = googleTokenVerifyService;
        this.tokenGeneratorService = tokenGeneratorService;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam("token_id") String tokenId) throws GeneralSecurityException, IOException {
        // if(googleTokenVerifyService.verifyToken(tokenId))
        // {
            return tokenGeneratorService.generateToken();
        //}
        //  else
        // return "";
    }
}
