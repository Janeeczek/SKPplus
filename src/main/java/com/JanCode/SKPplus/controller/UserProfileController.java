package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.Authentication.MyDaoAuthenticationProvider;
import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.service.ActiveUserService;
import com.JanCode.SKPplus.service.UserService;
import com.JanCode.SKPplus.web.dto.UserUpdateProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private ActiveUserService activeUserService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    MyDaoAuthenticationProvider authenticationProvider;
    @GetMapping("/profile")
    public ModelAndView showProfileDefaultWithParam(@RequestParam(required = false) String u,Authentication authentication) {
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
    public ModelAndView showProfileEdit(@RequestParam(required = false) String u,Authentication authentication) {
        ModelAndView model = new ModelAndView("/user/editProfile");
        MyUserPrincipal principal;
        if  (u == null) {
            principal = (MyUserPrincipal) authentication.getPrincipal();
            //System.out.println(principal.getAuthorities());
        } else {
            principal = new MyUserPrincipal(userService.findByUsername(u));
        }
        //model.addObject("activeService",activeUserService);
        //UserUpdateProfileDto userUpdateProfileDto;

        model.addObject("user",principal);
        model.addObject("userDto", new UserUpdateProfileDto(principal));
        return model;
    }
    @PostMapping("/save-profile/{u}")
    public String getProfileUpdate(@PathVariable String u, @ModelAttribute @Valid UserUpdateProfileDto userDto, @RequestParam(value="action", required=true) String action, BindingResult bindingResult, HttpServletRequest request ) {
        if (action.equals("save")) {
            userService.save(userDto,u);
            MyUserPrincipal principal = (MyUserPrincipal)userDetailsService.loadUserByUsername(userDto.getUsername());
            authWithAuthManager(request,principal);
        }

        if (action.equals("cancel")) {

        }


        return "redirect:/profile?"+ u;
    }
    public void authWithAuthManager(HttpServletRequest request, MyUserPrincipal principal) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
