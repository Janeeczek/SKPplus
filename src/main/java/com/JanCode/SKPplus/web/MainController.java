package com.JanCode.SKPplus.web;

import com.JanCode.SKPplus.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class MainController  {

    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
    }
    @GetMapping("/")
    public String root() {

        return "/user/user";
    }

    @GetMapping("/index.html")
    public String rootsecond() {

        return "/user/user";
    }

    @GetMapping("/user/user")
    public String user() {
        return "/user/user";
    }
    @GetMapping("/user")
    public String users() {

        return "/user/user";
    }
    @GetMapping("/utilities-color")
    public String utilities_color() {
        return "/user/utilities-color";
    }
    @GetMapping("/utilities-border")
    public String utilities_border() {
        return "/user/utilities-border";
    }
    @GetMapping("/utilities-animation")
    public String utilities_animation() {
        return "/user/utilities-animation";
    }
    @GetMapping("/utilities-other")
    public String utilities_other() {
        return "/user/utilities-other";
    }
    @GetMapping("/buttons")
    public String buttons() {
        return "/user/buttons";
    }
    @GetMapping("/cards")
    public String cards() {
        return "/user/cards";
    }
}