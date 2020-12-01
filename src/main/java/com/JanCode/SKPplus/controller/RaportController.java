package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.model.Raport;
import com.JanCode.SKPplus.service.RaportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RaportController {
    @Autowired
    private RaportServiceImpl reportService;

    @GetMapping("/raport/pokazWszystkie")
    public ModelAndView pokazStrone() {
        ModelAndView modelAndView = new ModelAndView("/user/raporty");
        modelAndView.addObject("raporty", reportService.getAllRaports());
        return modelAndView;
    }
    @GetMapping("/raport/usunRaport/{id}")
    public String usunRaport(@PathVariable long id) {
        reportService.removeById(id);
        return "redirect:/raport/pokazWszystkie";
    }
}
