package com.Nearsoft.referrals.service.impl;

import com.Nearsoft.referrals.service.GoogleTokenVerifyService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Arrays;

@Service
public class GoogleTokenVerifyServiceImpl implements GoogleTokenVerifyService {

    private static final JacksonFactory jacksonFactory = new JacksonFactory();
    private String iosClientId;
    private String androidClientId;

    public GoogleTokenVerifyServiceImpl(@Value("${ios.clientId}") String iosId,@Value("${android.clientId}") String androidClientId) {
        this.iosClientId = iosId;
        this.androidClientId = androidClientId;
    }

    @Override
    public Boolean verifyToken(String idTokenString) throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),jacksonFactory)
            // Specify the CLIENT_ID of the apps that accesses the backend:
            .setAudience(Arrays.asList(iosClientId, androidClientId))
            .build();

        // (Receive idTokenString by HTTPS POST)

        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            System.out.println("------------------------------------------------------------------------------------");
            System.out.println(email);


        } else {
           return false;
        }
        return true;
    }
}
