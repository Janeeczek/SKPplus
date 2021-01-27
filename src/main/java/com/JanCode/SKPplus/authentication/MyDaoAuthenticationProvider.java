package com.JanCode.SKPplus.authentication;


import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.service.ActiveUserService;
import com.JanCode.SKPplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class MyDaoAuthenticationProvider extends DaoAuthenticationProvider {


    @Autowired
    private UserService userService;
    @Autowired
    private ActiveUserService activeUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication.getPrincipal().toString() != null && !authentication.getPrincipal().toString().equals("")) {
            User user = userService.findByUsername(authentication.getPrincipal().toString());
            if (user != null) {
                if(user.isEnabled() == false) {
                    System.out.println("Ten użytkownik nie jest aktywowany: " + user.getUserName());
                }

                if(activeUserService.findByUsername(user.getUserName())==null) {
                    System.out.println("Dodano uzytkownika: " + user.getUserName() + ", do bazy danych aktywnych użytkowników!");
                    activeUserService.save(user);
                }
                System.out.println("Zalogowano uzytkownika: " + user.getUserName());
            }
        } else {
            System.out.println("DAO! Błąd! Nie znaleziono użytkownika: " + authentication.getPrincipal().toString());
        }


        return super.authenticate(authentication);
    }
}