package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.model.ActiveUsers;
import com.JanCode.SKPplus.model.InfoModel.WykresKolowyData;
import com.JanCode.SKPplus.model.InfoModel.WykresLiniowyData;
import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.model.Raport;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.repository.ActiveUsersRepository;
import com.JanCode.SKPplus.service.ActiveUserService;
import com.JanCode.SKPplus.service.RaportServiceImpl;
import com.JanCode.SKPplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice("com.JanCode.SKPplus.controller")
public class AnnotationAdvice {
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
            //userService.updateLastActiveTime(principal.getEmail()); //Chyba to niszczylo error page
            return principal;
        }
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
}