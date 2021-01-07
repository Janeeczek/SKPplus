package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.event.OnRegistrationCompleteEvent;
import com.JanCode.SKPplus.exeception.UserAlreadyExistException;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.service.UserService;
import com.JanCode.SKPplus.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
@Controller
@RequestMapping("/register*")
public class UserRegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }

    @PostMapping
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav;
        if (bindingResult.hasErrors()) {
            mav = new ModelAndView("register", "user", userDto);
            return mav;
        }
        try {
            User registered = userService.registerNewUserAccount(userDto);
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
        } catch (UserAlreadyExistException uaeEx) {
            mav = new ModelAndView("register", "user", userDto);
            mav.addObject("ErrorMessage", "Konto o podanej nazwie użytkownika lub adresie email już istnieje!");
            return mav;
        } catch (RuntimeException ex) {
            System.out.println(ex);
            return new ModelAndView("register", "user", userDto);
        }
        mav = new ModelAndView("register", "user", userDto);
        mav.addObject("SuccessMessage", "Sprawdz swój email aby potwierdzić konto!");
        return mav;
    }
}

