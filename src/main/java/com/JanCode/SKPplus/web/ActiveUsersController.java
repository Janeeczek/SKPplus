package com.JanCode.SKPplus.web;

import com.JanCode.SKPplus.model.ActiveUsers;
import com.JanCode.SKPplus.repository.ActiveUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/active")
public class ActiveUsersController {

    @Autowired
    private ActiveUsersRepository activeUsersRepository;


    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
    }
    @ModelAttribute("activeUsers")
    public List<ActiveUsers> getActiveUsers() {
        List<ActiveUsers> a = activeUsersRepository.getAllActiveUsers();
        //List<String>
        return (a == null) ? null : a;
    }

    @GetMapping
    public String showActive() {

        return "/admin/active";
    }
}
