package com.JanCode.SKPplus.Listeners;


import com.JanCode.SKPplus.model.ActiveUsers;
import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.repository.ActiveUsersRepository;
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
    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event)
    {
        List<SecurityContext> lstSecurityContext = event.getSecurityContexts();
        for (SecurityContext securityContext : lstSecurityContext)
        {
            MyUserPrincipal principal = (MyUserPrincipal) securityContext.getAuthentication().getPrincipal();;
            //User user = new User (userRepository.findByUsername(authentication.getPrincipal().toString()));
            System.out.println("WYLOGOWANO! email: " + principal.getEmail());
            if(activeUserService.findByEmail(principal.getEmail())!=null) {
                System.out.println("Usunięto email: " + principal.getEmail() + " z bazy danych aktywnych użytkowników!");
                activeUserService.delete(principal);
                userService.updateLastActiveTime(principal.getEmail());
            }
        }
    }

}