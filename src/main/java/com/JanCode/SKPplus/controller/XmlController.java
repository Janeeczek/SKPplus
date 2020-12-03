package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.model.Raport;
import com.JanCode.SKPplus.service.RaportService;
import com.JanCode.SKPplus.service.RaportServiceImpl;
import com.JanCode.SKPplus.web.dto.RaportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class XmlController {
    @Autowired
    private RaportServiceImpl reportService;
    @GetMapping("/raport/createFromFile/{id}")
    public ModelAndView showUpload(Authentication authentication, @PathVariable String id) {
        MyUserPrincipal principal;
        ModelAndView model = new ModelAndView("/user/user");

        principal = (MyUserPrincipal) authentication.getPrincipal();

        Raport raport = reportService.createRaport(id,principal.getUsername());

        return model;
    }
    @GetMapping("/raport/createFromFileTEST")
    public ModelAndView showUpload() {
        ModelAndView model = new ModelAndView("/user/user");



        Raport raport = reportService.createTESTRaport();

        return model;
    }
    @PostMapping("/upload-save")
    public ModelAndView getProfileUpdate(@ModelAttribute @Valid RaportDto raportDto, @RequestParam(value="action", required=true) String action, BindingResult bindingResult, HttpServletRequest request ) {
        if (action.equals("save")) {
        }

        if (action.equals("cancel")) {
        }
        ModelAndView model = new ModelAndView("/");

        return model;
    }
    @GetMapping("/delete")
    public ModelAndView showDelete(Authentication authentication) {
        MyUserPrincipal principal;
        ModelAndView model = new ModelAndView("/user/user");

        principal = (MyUserPrincipal) authentication.getPrincipal();
        reportService.removeAll();

        return model;
    }

}
