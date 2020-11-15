package com.JanCode.SKPplus.Handlers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationHandler {

    public void authenticate (Authentication authentication) {
        System.out.println("ZALOGOWANO!");
    }

}