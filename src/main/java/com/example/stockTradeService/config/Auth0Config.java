package com.example.stockTradeService.config;

import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.auth0.net.TokenRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Auth0Config {

    @Value("${auth0.domain}")
    private String domain;

    @Value("${auth0.clientId}")
    private String clientId;

    @Value("${auth0.clientSecret}")
    private String clientSecret;

    @Bean
    public AuthAPI authAPI() {
        return AuthAPI.newBuilder(domain, clientId, clientSecret).build();
    }

    @Bean
    public ManagementAPI managementAPI() throws Auth0Exception {
        AuthAPI authAPI = authAPI();
        TokenRequest tokenRequest = authAPI.requestToken("https://"+domain+"/api/v2/");
        TokenHolder holder = tokenRequest.execute().getBody();
        String accessToken = holder.getAccessToken();
        return ManagementAPI.newBuilder(domain, accessToken).build();
    }
}
