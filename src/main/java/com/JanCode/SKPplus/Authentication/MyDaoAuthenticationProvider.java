package com.JanCode.SKPplus.Authentication;

import com.JanCode.SKPplus.Handlers.AuthenticationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class MyDaoAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    AuthenticationHandler authenticationHandler;    // <- authenticationHandler is null, is not resolved

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("ZALOGOWANO!");
        return super.authenticate(authentication);
    }
}