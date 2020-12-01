package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.model.ActiveUsers;
import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.repository.ActiveUsersRepository;
import com.JanCode.SKPplus.service.ActiveUserService;
import com.JanCode.SKPplus.service.RaportServiceImpl;
import com.JanCode.SKPplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

@ControllerAdvice("com.JanCode.SKPplus.controller")
public class AnnotationAdvice {

    @Autowired
    private ActiveUsersRepository activeUsersRepository;
    @Autowired
    private ActiveUserService activeUserService;
    @Autowired
    private UserService userService;
    @Autowired
    private RaportServiceImpl raportService;
    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication) {

        if (authentication == null) {
            return null;
        } else {
            MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
            userService.updateLastActiveTime(principal.getEmail());
            return principal;
        }
    }
    @ModelAttribute("activeUsers")
    public List<ActiveUsers> getActiveUsers() {
        List<ActiveUsers> a = activeUsersRepository.getAllActiveUsers();
        return (a == null) ? null : a;
    }
    @ModelAttribute("image")
    public String getCurrentUserPhoto(Authentication authentication) {
        if (authentication == null)
            return null;
        else {
            MyUserPrincipal principal = (MyUserPrincipal)  authentication.getPrincipal();
            return principal.getByte64Image();
        }
    }
    @ModelAttribute("allIncome")
    public double getAllIncome() {
        //if(raportService.getAllIncome() > 0)
        return raportService.getAllIncome();
        //return 0;
    }
}