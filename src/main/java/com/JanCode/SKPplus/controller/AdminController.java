package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController extends MainController{
    @Autowired
    private SessionRegistry sessionRegistry;
    @Autowired
    private UserService userService;
    @GetMapping
    public String showAdmin() {
        return "/admin/admin";
    }
    @GetMapping("/terminate/{username}")
    public String terminate(@PathVariable(required = false) String username) {
        /*
        System.out.println("USERNAME = " + username);
        UserDetails userDetails = (UserDetails) new MyUserPrincipal(userService.findByUsername(username));
        System.out.println("principal.getUsername() = " +userDetails.getUsername());
        if(userDetails != null)
        {

            for (SessionInformation information : sessionRegistry.getAllSessions(userDetails, true)) {
                System.out.println("EXPIRE MPW");
                information.expireNow();
            }

        }

         */
        for (Object principal : sessionRegistry.getAllPrincipals()) {
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                if (userDetails.getUsername().equals(username)) {

                    for (SessionInformation information : sessionRegistry.getAllSessions(userDetails, true)) {
                        System.out.println("userDetails.getUsername() = " + userDetails.getUsername());
                        information.expireNow();
                    }
                }
            }
        }


        return "redirect:/admin?active";

    }
}
