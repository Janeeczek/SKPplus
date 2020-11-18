package com.JanCode.SKPplus.service;
import javax.servlet.http.HttpSessionEvent;

import com.JanCode.SKPplus.repository.ActiveUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class MyHttpSessionEventPublisher extends HttpSessionEventPublisher {
    @Autowired
    private ActiveUsersRepository activeUsersRepository;
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        //System.out.println("SESJA UTWORZONA!" + event.getSession().getId()+"    " +event.getSource().toString());
        super.sessionCreated(event);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        //System.out.println("SESJA ZAKONCZONA!" + event.getSession().getId()+"    " +event.getSource().toString());
        //System.out.println("ATRYBUT NAZWA"+event.getSession().getAttributeNames());
        super.sessionDestroyed(event);
    }
    //https://stackoverflow.com/questions/21989966/customize-session-registry-spring-security uzyj tegp
}