package com.JanCode.SKPplus.listener;


import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.service.ActiveUserService;
import com.JanCode.SKPplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LogoutListener implements ApplicationListener<SessionDestroyedEvent> {

    @Autowired
    private ActiveUserService activeUserService;

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event)
    {
        List<SecurityContext> lstSecurityContext = event.getSecurityContexts();
        for (SecurityContext securityContext : lstSecurityContext)
        {

            MyUserPrincipal principal = (MyUserPrincipal) securityContext.getAuthentication().getPrincipal();
            if(activeUserService.findByUsername(principal.getUsername())!=null) {
                System.out.println("Usunięto uzytkownika: " + principal.getUsername() + ", z bazy danych aktywnych użytkowników!");
                activeUserService.delete(principal);
                System.out.println("Wylogowano uzytkownika: " + principal.getUsername());
            }
        }
    }

}