package com.example.stockTradeService.rest;

import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.example.stockTradeService.service.Auth0Service;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth0")
public class Auth0Controller {

    @Autowired
    private Auth0Service auth0Service;

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/signIn")
    public ResponseEntity<Void> signIn(HttpServletResponse response) throws IOException {
        String url = auth0Service.signInUrl();
        response.sendRedirect(url);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getToken")
    public ResponseEntity<String> getJwt(@RequestParam String authCode) throws Auth0Exception {
        TokenHolder tokenHolder = auth0Service.fetchJwt(authCode);
        String jwt = tokenHolder.getAccessToken();
        return ResponseEntity.ok(jwt);
    }
}
