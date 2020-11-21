package com.JanCode.SKPplus.web;

import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.service.ActiveUserService;
import com.JanCode.SKPplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class UserProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private ActiveUserService activeUserService;

    @GetMapping("/profile")
    public ModelAndView showProfileDefaultWithParam(@RequestParam(required = false) String u,Authentication authentication)
    {
        MyUserPrincipal principal;
        ModelAndView model = new ModelAndView("/user/profile");

        if  (u == null) {
            principal = (MyUserPrincipal) authentication.getPrincipal();
            //System.out.println(principal.getAuthorities());
        } else {
            principal = new MyUserPrincipal(userService.findByUsername(u));
        }
        model.addObject("activeService",activeUserService);
        model.addObject("user",principal);

        return model;
    }
    @GetMapping("/profile{u}/edit")
    public ModelAndView showProfileEdit(@RequestParam(required = false) String u,Authentication authentication)
    {
        ModelAndView model = new ModelAndView("/user/profile/edit");
        return model;
    }
}
