package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.service.UserService;
import com.JanCode.SKPplus.token.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
@Controller
public class ConfirmRegistrationController {
    @Autowired
    private UserService service;

    @GetMapping("/registrationConfirm")
    public ModelAndView confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) {

        ModelAndView mav = new ModelAndView("newLogin");
        VerificationToken verificationToken = service.getVerificationToken(token);
        if (verificationToken == null) {
            model.addAttribute("InvalidMessage", "Link aktywacyjny jest niepoprawny!");
            return mav;
        }

        User user = verificationToken.getUser();
        System.out.println(user.getUsername());
        Calendar cal = Calendar.getInstance();
        //Date date = java.util.Date.from(verificationToken.getExpiryDate().atZone(ZoneId.systemDefault()).toInstant());
        if ((java.util.Date.from(verificationToken.getExpiryDate().atZone(ZoneId.systemDefault()).toInstant()).getTime() - cal.getTime().getTime()) <= 0) {
            model.addAttribute("ExpiredMessage", "Link aktywacyjny utracił swoją ważność!");
            return mav;
        }

        user.setEnabled(true);
        service.saveRegisteredUser(user);
        model.addAttribute("SuccessMessage", "Konto zostało aktywowane!");
        return mav;
    }
}
