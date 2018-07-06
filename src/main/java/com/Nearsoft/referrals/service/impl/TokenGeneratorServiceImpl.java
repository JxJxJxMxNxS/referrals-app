package com.Nearsoft.referrals.service.impl;

import com.Nearsoft.referrals.service.TokenGeneratorService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenGeneratorServiceImpl implements TokenGeneratorService {


    @Override
    public String generateToken() {
      /*  String token;
        String uri = "http://loclahost:8080/oauth/token";
        RestTemplate restTemplate = new RestTemplate();
        token = restTemplate.getForObject(uri, String.class);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("email", "first.last@example.com");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );*/ return null;
    }
}
