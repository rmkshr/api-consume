package com.srv.apiconsume.consume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class ConsumeToken {

    @Autowired
    private RestTemplate restTemplate;

    public void token() {

        // API URL
        String url = "https://login.microsoftonline.com/8c71de67/oauth2/token";

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.add("Cookie", "fpc=AiltHCF1fxxCnPHLs52wRhzoXQMfAQAAAG_JK98OAAAA");

        // Body (Form Data)
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", "135d6e7d-9");
        body.add("client_secret", "o9n8");
        body.add("resource", "api://b2b031fd-1");
        body.add("grant_type", "client_credentials");

        // HttpEntity with headers and body
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        // POST Request
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        // Log the result
        System.out.println("Response from API: " + response.getBody());
    }
}