package com.nearsoft.referrals.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.nearsoft.referrals.exception.ReferralsAppException;
import com.nearsoft.referrals.model.User;
import com.nearsoft.referrals.service.GoogleUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

@Service
public class GoogleUserServiceImpl implements GoogleUserService {

    private static final JacksonFactory jacksonFactory = new JacksonFactory();
    private static final Logger logger = LoggerFactory.getLogger(GoogleUserServiceImpl.class);
    private String iosClientId;
    private String androidClientId;

    public GoogleUserServiceImpl(@Value("${ios.clientId}") String iosId, @Value("${android.clientId}") String androidClientId) {
        this.iosClientId = iosId;
        this.androidClientId = androidClientId;
    }

    @Override
    public User verifyTokenAndCreateUser(String idTokenString) throws GeneralSecurityException, IOException {
        User user;
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), jacksonFactory)
                .setAudience(Arrays.asList(iosClientId, androidClientId))
                .build();


        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken == null) {
            throw new ReferralsAppException("Google token is invalid or has expired");
        }

        Payload payload = idToken.getPayload();

        user = new User();
        user.setEmail(payload.getEmail());
        user.setName((String) (payload.get("name")));


        return user;


    }
}
