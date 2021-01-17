package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.authentication.MyDaoAuthenticationProvider;
import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.service.ActiveUserService;
import com.JanCode.SKPplus.service.UserService;
import com.JanCode.SKPplus.web.dto.UserUpdateProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class UserProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private ActiveUserService activeUserService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private MyDaoAuthenticationProvider authenticationProvider;

    @GetMapping("/profile")
    public ModelAndView showProfileDefaultWithoutParam(@RequestParam Map<String,String> allParams, Authentication authentication) {
        MyUserPrincipal principal = null;
        ModelAndView model = new ModelAndView("/user/profile");
        if (allParams.isEmpty()) {
            principal = (MyUserPrincipal) authentication.getPrincipal();
            model.addObject("activeService",activeUserService);
            model.addObject("user",principal);
            return model;
        } else {
            for(String username : allParams.keySet()) {
                principal = new MyUserPrincipal(userService.findByUsername(username));
                break;
            }
            if(principal.getUser() != null ) {
                model.addObject("activeService",activeUserService);
                model.addObject("user",principal);

                return model;
            } else {
                principal = (MyUserPrincipal) authentication.getPrincipal();
                model.addObject("activeService",activeUserService);
                model.addObject("user",principal);
                model.addObject("ErrorMessage", "Wyszukany użytkownik nie istnieje!\n Wyświetlam profil aktywnego użytkownika.");
                return model;
            }
        }
    }

    @GetMapping("/profile/edit")
    public ModelAndView showProfileEdit(@RequestParam Map<String,String> allParams, Authentication authentication) {
        ModelAndView model = new ModelAndView("/user/editProfile");
        MyUserPrincipal requestPrincipal = null;
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        if(sourcePrincipal.getUser() != null) { //Jeśli użytkownik zleceniający jest uwierzytelniony
            if (allParams.isEmpty()) { //Jeśli nie ma parametrów
                requestPrincipal = sourcePrincipal;
                model.addObject("user",requestPrincipal);
                model.addObject("userDto", new UserUpdateProfileDto(requestPrincipal));
                return model;
            } else { //Jeśli są parametry
                for(String username : allParams.keySet()) {
                    requestPrincipal = new MyUserPrincipal(userService.findByUsername(username));
                    break;
                }
                if(requestPrincipal.getUser() != null ) { //Jeśli znaleziono uzytkownika z parametru
                    if(requestPrincipal.getUsername() == sourcePrincipal.getUsername()) {  //Jeśli użytkownik z parametru to uwierzytelniony uzytkownik
                        requestPrincipal = sourcePrincipal;
                        model.addObject("user",requestPrincipal);
                        model.addObject("userDto", new UserUpdateProfileDto(requestPrincipal));
                        return model;
                    } else {  //Jeśli użytkownik z parametru to NIE uwierzytelniony uzytkownik
                        if (sourcePrincipal.isAdmin()) { //Jeśli uwierzytelniony uzytkownik to admin
                            model.addObject("user",requestPrincipal);
                            model.addObject("userDto", new UserUpdateProfileDto(requestPrincipal));
                            return model;
                        } else { //Jeśli uwierzytelniony uzytkownik to NIE admin
                            requestPrincipal = sourcePrincipal;
                            model.addObject("user",requestPrincipal);
                            model.addObject("userDto", new UserUpdateProfileDto(requestPrincipal));
                            model.addObject("ErrorMessage", "Nie masz uprawnień do edytowania profilu innych użytkowników! Wyświetlam edycje profil aktywnego użytkownika.");
                            return model;
                        }
                    }
                } else { //Jeśli nie znaleziono uzytkownika z parametru
                    requestPrincipal = sourcePrincipal;
                    model.addObject("user",requestPrincipal);
                    model.addObject("userDto", new UserUpdateProfileDto(requestPrincipal));
                    model.addObject("ErrorMessage", "Nie znaleziono tego użytkowanika do edytowania! Wyświetlam edycje profil aktywnego użytkownika.");
                    return model;
                }
            }
        } else { //Jeśli użytkownik zleceniający NIE jest uwierzytelniony
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "NIE MASZ TAKICH UPRAWNIEN!");
        }
    }
    @PostMapping("/save-profile/{u}")
    public ModelAndView getProfileUpdate(@ModelAttribute @Valid UserUpdateProfileDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes,@PathVariable String u, @RequestParam(value="action", required=true) String action,  HttpServletRequest request, Authentication authentication ) {
        ModelAndView model;
        if (action.equals("save")) {
            if (bindingResult.hasErrors()) {
                model = new ModelAndView("redirect:/profile?"+u);
                redirectAttributes.addFlashAttribute("ErrorMessage", "Błąd podczas aktualizowania profilu! Spróbuj jeszcze raz");
                return model;
            }
            redirectAttributes.addFlashAttribute("SuccessMessage", "Pomyślnie aktualizowano profil!");
            model = new ModelAndView("redirect:/profile?"+u);
            userService.saveUpdatedUser(userDto,u);
            MyUserPrincipal principal = (MyUserPrincipal)userDetailsService.loadUserByUsername(userDto.getUsername());
            authWithAuthManager(request,principal);
            return model;
        }

        if (action.equals("cancel")) {
            redirectAttributes.addFlashAttribute("SuccessMessage", "Nie zmieniono żadnych danych!");
            model = new ModelAndView("redirect:/profile?"+u);
            return model;
        }
        redirectAttributes.addFlashAttribute("ErrorMessage", "Błąd! Nie zmieniono żadnych danych!");
        model = new ModelAndView("redirect:/profile?"+u);
        return model;
    }
    public void authWithAuthManager(HttpServletRequest request, MyUserPrincipal principal) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
