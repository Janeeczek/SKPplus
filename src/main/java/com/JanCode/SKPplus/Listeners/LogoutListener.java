package com.JanCode.SKPplus.Listeners;


import com.JanCode.SKPplus.model.ActiveUsers;
import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.repository.ActiveUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LogoutListener implements ApplicationListener<SessionDestroyedEvent> {

    @Autowired
    private ActiveUsersRepository activeUsersRepository;

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event)
    {
        List<SecurityContext> lstSecurityContext = event.getSecurityContexts();
        MyUserPrincipal principal;
        for (SecurityContext securityContext : lstSecurityContext)
        {
            principal = (MyUserPrincipal) securityContext.getAuthentication().getPrincipal();
            System.out.println("WYLOGOWANO! email: " + principal.getEmail());
            ActiveUsers active = new ActiveUsers(principal.getEmail());
            if(activeUsersRepository.findByEmail(active.getEmail())!=null) {
                System.out.println("Usunięto email: " + active.getEmail() + " z bazy danych aktywnych użytkowników!");
                activeUsersRepository.myremoveByEmail(active.getEmail());
            }
        }
    }

}