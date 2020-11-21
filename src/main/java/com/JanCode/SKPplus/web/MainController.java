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

    @GetMapping("/")
    public String showRoot() {

        return "/user/user";
    }
    @GetMapping("/test")
    public String showTest() {

        return "/test";
    }
    @GetMapping("/charts")
    public String showCharts() {

        return "/user/charts";
    }
    @GetMapping("/tables")
    public String showTables() {

        return "/user/tables";
    }
    @GetMapping("/utilities-color")
    public String showUtilities_color() {
        return "/user/utilities-color";
    }
    @GetMapping("/utilities-border")
    public String showUtilities_border() {
        return "/user/utilities-border";
    }
    @GetMapping("/utilities-animation")
    public String showUtilities_animation() {
        return "/user/utilities-animation";
    }
    @GetMapping("/utilities-other")
    public String showUtilities_other() {
        return "/user/utilities-other";
    }
    @GetMapping("/buttons")
    public String showButtons() {
        return "/user/buttons";
    }
    @GetMapping("/cards")
    public String showCards() {
        return "/user/cards";
    }
}