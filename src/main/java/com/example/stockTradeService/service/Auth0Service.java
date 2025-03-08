package com.example.stockTradeService.service;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.auth0.net.Request;
import com.auth0.net.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Auth0Service {

    private final AuthAPI authAPI;


    @Value("${auth0.redirectUri}")
    private String redirectUri;

    @Value("${auth0.audience}")
    private String audience;

    public Auth0Service(AuthAPI authAPI) {
        this.authAPI = authAPI;
    }

    public String signInUrl() {
        return authAPI.authorizeUrl(redirectUri).withAudience(audience).build();
    }

    public TokenHolder fetchJwt(String authCode) {
        try {
            Response<TokenHolder> response = authAPI.exchangeCode(authCode, redirectUri).setAudience(audience).execute();
            return response.getBody();
        } catch (Auth0Exception e) {
            throw new RuntimeException(e);
        }
    }

}
