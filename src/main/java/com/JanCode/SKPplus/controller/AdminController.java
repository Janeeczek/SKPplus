package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.event.OnRegistrationCompleteEvent;
import com.JanCode.SKPplus.exeception.UserAlreadyExistException;
import com.JanCode.SKPplus.model.ActiveUsers;
import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.service.ActiveUserService;
import com.JanCode.SKPplus.service.UserService;
import com.JanCode.SKPplus.web.dto.AdminRegistrationDto;
import com.JanCode.SKPplus.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController extends MainController{
    @Autowired
    private SessionRegistry sessionRegistry;
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private ActiveUserService activeUserService;

    @GetMapping
    public ModelAndView showAdmin(@RequestParam String param) {
        ModelAndView modelAndView = new ModelAndView("/admin/admin","tryb",param);
        if (param.equals("createUser")) {
            modelAndView.addObject("userDto",new AdminRegistrationDto());
        } else if(param.equals("showAll")) {
            List<User> a = userService.findAllUsers();

            modelAndView.addObject("allUsers",a);
        } else if(param.equals("showActive")) {
            List<ActiveUsers> a = activeUserService.findAll();
            modelAndView.addObject("activeUsers",a);
        }
        return modelAndView;

    }
    @GetMapping("/terminate/{username}")
    public ModelAndView terminate(@PathVariable(required = false) String username) {
        ModelAndView modelAndView = new ModelAndView("/admin/admin","tryb","showActive");

        for (Object principal : sessionRegistry.getAllPrincipals()) {
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                if (userDetails.getUsername().equals(username)) {
                    for (SessionInformation information : sessionRegistry.getAllSessions(userDetails, true)) {
                        information.expireNow();
                    }
                    modelAndView.addObject("SuccessMessage", "Użytkownik został wylogowany!");
                    List<ActiveUsers> a = activeUserService.findAll();
                    modelAndView.addObject("activeUsers",a);
                    return modelAndView;
                }
            }
        }
        modelAndView.addObject("ErrorMessage", "Nie można wylogować użytkownika!");
        List<ActiveUsers> a = activeUserService.findAll();
        modelAndView.addObject("activeUsers",a);
        return modelAndView;
    }
    @GetMapping("/delete/{username}")
    public ModelAndView delete(@PathVariable(required = false) String username) {
        ModelAndView modelAndView = new ModelAndView("/admin/admin","tryb","showAll");
        if (username != null && !username.isBlank()) {
            userService.delete(username);
            modelAndView.addObject("SuccessMessage", "Użytkownik usunięty!");
            return modelAndView;
        }
        modelAndView.addObject("ErrorMessage", "Nie można usunąć użytkownika!");

        return modelAndView;
    }
    @GetMapping("/activate/{username}")
    public ModelAndView forceActivate(@PathVariable(required = false) String username) {
        ModelAndView modelAndView = new ModelAndView("/admin/admin","tryb","showAll");
        if (username != null && !username.isBlank()) {
            userService.saveRegisteredUser(username);
            modelAndView.addObject("SuccessMessage", "Użytkownik został aktywowany!");
            return modelAndView;
        }
        modelAndView.addObject("ErrorMessage", "Nie można aktywować użytkownika!");

        return modelAndView;
    }
    @PostMapping("/createUser")
    public ModelAndView createUserAccount(@ModelAttribute("userDto") @Valid AdminRegistrationDto userDto, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView modelAndView;
        System.out.println("Role = " + userDto.getRole());
        if (bindingResult.hasErrors()) {
            modelAndView = new ModelAndView("/admin/admin","tryb","createUser");
            modelAndView.addObject("userDto",userDto);
            return modelAndView;
        }
        try {
            User registered = userService.registerNewUserAccount(userDto);
            if (!userDto.isActive()) {
                String appUrl = request.getContextPath();
                eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
            }

        } catch (UserAlreadyExistException uaeEx) {
            modelAndView = new ModelAndView("/admin/admin","tryb","createUser");
            modelAndView.addObject("userDto",userDto);
            modelAndView.addObject("ErrorMessage", "Konto o podanej nazwie użytkownika lub adresie email już istnieje!");
            return modelAndView;
        } catch (RuntimeException ex) {
            System.out.println(ex);
            modelAndView = new ModelAndView("/admin/admin","tryb","createUser");
            modelAndView.addObject("userDto",userDto);
            return modelAndView;
        }
        modelAndView = new ModelAndView("/admin/admin","tryb","createUser");
        modelAndView.addObject("userDto",userDto);
        if (!userDto.isActive()) modelAndView.addObject("SuccessMessage", "Konto zostało utworzone i czeka na aktywacje!");
        else modelAndView.addObject("SuccessMessage", "Konto zostało utworzone i aktywowane!");
        return modelAndView;
    }
}
