package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.model.AccountType;
import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.service.ActiveUserService;
import com.JanCode.SKPplus.service.RaportServiceImpl;
import com.JanCode.SKPplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class MainController  {

    @Autowired
    private UserService userService;
    @Autowired
    private RaportServiceImpl raportService;
    @Autowired
    private ActiveUserService activeUserService;

    @GetMapping("/")
    public ModelAndView showUser(Authentication authentication) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if(mode == AccountType.ADMIN) {
                modelAndView = new ModelAndView("redirect:/admin?tryb=showMain");
                return modelAndView;
            }
            modelAndView = new ModelAndView("user/user","mode",mode.name());
            if( mode == AccountType.USER) {
                modelAndView.addObject("allIncome",raportService.getAllIncomeString());
                modelAndView.addObject("activeEmp",activeUserService.findAllEmployees());
            }


        } else {
            modelAndView = new ModelAndView("error");
        }
        return modelAndView;

    }
    @GetMapping("/wykresy")
    public ModelAndView showUserWykresy(Authentication authentication) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();

            if( mode == AccountType.USER) {
                modelAndView = new ModelAndView("user/wykresy","mode",mode.name());
                return modelAndView;
            }
            modelAndView = new ModelAndView("user/user","mode",mode.name());


        } else {
            modelAndView = new ModelAndView("error");
        }
        return modelAndView;
    }





}