package com.JanCode.SKPplus.web;

import com.JanCode.SKPplus.model.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/blank")
public class BlankController {
    /*
    @Autowired
    private SessionRegistry sessionRegistry;

     */
    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
    }


    @GetMapping
    public String showBlank(Locale locale, Model model) {
    /*
        final List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        List<String> allUsernames = new ArrayList<String>();
        System.out.println(allUsernames.size());
        for (final Object principal : allPrincipals) {
            if (principal instanceof MyUserPrincipal) {
                final MyUserPrincipal user = (MyUserPrincipal) principal;
                List<SessionInformation> activeUserSessions = sessionRegistry.getAllSessions(principal, false);
                if (!activeUserSessions.isEmpty()) {
                    allUsernames.add(user.getUsername());
                    System.out.println(user.getUsername());
                }
            }
        }
        model.addAttribute("activeuser",  allUsernames);

     */
        return "blank";
    }
}
