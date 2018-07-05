package com.Nearsoft.referrals.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface GoogleTokenVerifyService {
    String verifyToken(String idTokenString) throws GeneralSecurityException, IOException;
}
