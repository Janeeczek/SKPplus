package com.JanCode.SKPplus.web;

import com.JanCode.SKPplus.model.ActiveUsers;
import com.JanCode.SKPplus.repository.ActiveUsersRepository;
import com.JanCode.SKPplus.service.ActiveUserService;
import com.JanCode.SKPplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@ControllerAdvice("com.JanCode.SKPplus.web")
public class AnnotationAdvice {

    @Autowired
    private ActiveUsersRepository activeUsersRepository;
    @Autowired
    private ActiveUserService activeUserService;


    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
    }
    @ModelAttribute("activeUsers")
    public List<ActiveUsers> getActiveUsers() {
        List<ActiveUsers> a = activeUsersRepository.getAllActiveUsers();
        return (a == null) ? null : a;
    }
}