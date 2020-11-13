package com.JanCode.SKPplus.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserLoginController {
    @Autowired
    private UserDetailsService userService;

    @GetMapping("/login*")
    public String login() {

        return "login";
    }
    /*
    @GetMapping("/login")
    public String loginSucces() {

        return "login?reg";
    }

     */


}
