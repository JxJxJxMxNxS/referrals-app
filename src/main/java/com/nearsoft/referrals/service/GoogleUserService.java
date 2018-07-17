package com.nearsoft.referrals.service;

import com.nearsoft.referrals.model.User;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface GoogleUserService {
    User verifyTokenAndCreateUser(String idTokenString) throws GeneralSecurityException, IOException;
}
