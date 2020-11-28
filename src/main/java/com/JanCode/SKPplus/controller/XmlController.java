package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.web.dto.RaportDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class XmlController {
    @GetMapping("/upload")
    public ModelAndView showUpload(Authentication authentication) {
        MyUserPrincipal principal;
        ModelAndView model = new ModelAndView("/user/uploadXML");

        principal = (MyUserPrincipal) authentication.getPrincipal();


        return model;
    }
    @PostMapping("/upload-save")
    public ModelAndView getProfileUpdate(@ModelAttribute @Valid RaportDto raportDto, @RequestParam(value="action", required=true) String action, BindingResult bindingResult, HttpServletRequest request ) {
        if (action.equals("save")) {
            System.out.println("SAVE");
        }

        if (action.equals("cancel")) {
            System.out.println("SAVE");
        }
        ModelAndView model = new ModelAndView("/");

        return model;
    }

}
