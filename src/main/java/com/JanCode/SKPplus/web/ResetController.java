package com.JanCode.SKPplus.web;

import com.JanCode.SKPplus.service.UserService;
import com.JanCode.SKPplus.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/reset")
public class ResetController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showResetForm() {
        //model.addAttribute("user", new UserRegistrationDto());
        return "forgot-password";
    }

    @PostMapping
    public String resetUserAccount() {
        return "redirect:/login?reset";
    }
}

