package com.nearsoft.referrals.service.impl;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.nearsoft.referrals.model.AppToken;
import com.nearsoft.referrals.service.TokenGeneratorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenGeneratorServiceImpl implements TokenGeneratorService {

    private String client_id;
    private String client_secret;
    private String base_url;

    public TokenGeneratorServiceImpl(@Value("${server.base-url}") String base_url, @Value("${security.jwt.client-id}") String client_id, @Value("${security.jwt.client-secret}") String client_secret) {
        this.base_url = base_url;
        this.client_id = client_id;
        this.client_secret = client_secret;
    }

    @Override
    public String generateToken(String email) {
        HttpHeaders headers = new HttpHeaders();

        String plainCreds = client_id + ":" + client_secret;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        headers.add("Authorization", "Basic " + base64Creds);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("username", email);
        map.add("password", email);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        AppToken app = restTemplate.postForObject(base_url + "oauth/token", request, AppToken.class);

        return app.getAccess_token();
    }
}
