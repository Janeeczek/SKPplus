package com.JanCode.SKPplus.web;

import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.service.UserService;
import com.JanCode.SKPplus.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.validation.Valid;
@Controller
@RequestMapping("/register*")
public class UserRegistrationController {

    @Autowired
    private UserService userService;


    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (userService.findByUsername(userDto.getUsername())!=null) {
            return "redirect:/register?usernameDuplicate";
        }
        if (userService.findByEmail(userDto.getEmail())!=null) {
            return "redirect:/register?emailDuplicate";
        }

            userService.save(userDto);
        return "redirect:/login?reg";

        //return "login";



    }
}

