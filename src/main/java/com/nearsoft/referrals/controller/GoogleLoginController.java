package com.nearsoft.referrals.controller;

import com.nearsoft.referrals.exception.ReferralsAppException;
import com.nearsoft.referrals.model.User;
import com.nearsoft.referrals.repository.UserRepository;
import com.nearsoft.referrals.service.GoogleUserService;
import com.nearsoft.referrals.service.TokenGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.util.Optional;


@RestController
public class GoogleLoginController {
    public static final String LOGOUT_SUCCESSFULLY_MESSAGE = "Logout successfully";
    public static final String ERROR_LOGOUT_MESSAGE = "An error occurred when trying to logout: User not found, try again";
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleLoginController.class);
    private GoogleUserService googleUserService;
    private TokenGeneratorService tokenGeneratorService;
    private UserRepository userRepository;

    public GoogleLoginController(GoogleUserService googleUserService, TokenGeneratorService tokenGeneratorService, UserRepository userRepository) {
        this.googleUserService = googleUserService;
        this.tokenGeneratorService = tokenGeneratorService;
        this.userRepository = userRepository;
    }


    @ResponseBody
    @RequestMapping(value = "/log_out", method = RequestMethod.POST)
    public ResponseEntity<String> logout(Principal principal) {
        User user = userRepository.findByemail(principal.getName());

        if (user != null) {
            LOGGER.trace("Database user: {}", user);
            user.setLogged(false);
            user = userRepository.save(user);
            LOGGER.trace("Updated user: {}", user);
            LOGGER.info("User {} successfully logged out", user);
            return new ResponseEntity<>(LOGOUT_SUCCESSFULLY_MESSAGE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ERROR_LOGOUT_MESSAGE, HttpStatus.BAD_REQUEST);
        }

    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestParam("token_id") String tokenId, @RequestParam(value = "token_device", required = false) Optional<String> tokenDevice) throws GeneralSecurityException, IOException {
        User databaseUser;
        User user = null;
        try {
            user = googleUserService.verifyTokenAndCreateUser(tokenId);
        } catch (ReferralsAppException referralsAppException) {
            LOGGER.warn("An error occurred during the login", referralsAppException);
            return new ResponseEntity(referralsAppException.getMessage(), HttpStatus.FORBIDDEN);
        }
        if (user != null) {
            LOGGER.trace("Google User: {}", user);
            databaseUser = userRepository.findByemail(user.getEmail());

            if (databaseUser != null) {
                LOGGER.trace("Database user: {}", databaseUser);
                user = databaseUser;
            }
            user.setLogged(true);
            if (tokenDevice.isPresent() && tokenDevice.get().compareTo("null") != 0) {
                user.setToken_device(tokenDevice.get());
            }
            user = userRepository.save(user);
            user.setToken(tokenGeneratorService.generateToken(user.getEmail()));

            LOGGER.trace("User saved: {}", user);
            LOGGER.info("User {} successfully logged in", user);
            return new ResponseEntity(user, HttpStatus.OK);
        } else
            return new ResponseEntity(user, HttpStatus.FORBIDDEN);
    }


}
