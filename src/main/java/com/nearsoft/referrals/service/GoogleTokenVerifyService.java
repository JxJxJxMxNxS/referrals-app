package com.nearsoft.referrals.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface GoogleTokenVerifyService {
    Boolean verifyToken(String idTokenString) throws GeneralSecurityException, IOException;
}
