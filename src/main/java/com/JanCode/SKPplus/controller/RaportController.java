package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.model.FileDB;
import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.model.Raport;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.service.FileStorageService;
import com.JanCode.SKPplus.service.RaportServiceImpl;
import com.JanCode.SKPplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class RaportController {
    @Autowired
    private RaportServiceImpl reportService;
    @Autowired
    private FileStorageService storageService;
    @Autowired
    private UserService userService;
    @GetMapping("/raport/pokazWszystkie")
    public ModelAndView pokazStrone() {
        ModelAndView modelAndView = new ModelAndView("/user/raporty");
        modelAndView.addObject("raporty", reportService.getAllRaports());
        return modelAndView;
    }
    @GetMapping("/raport/usunRaport/{id}")
    public String usunRaport(@PathVariable long id, RedirectAttributes atts) {
        reportService.removeById(id);
        atts.addFlashAttribute("successMsg", "Pomyslnie usunięto raport o id: "+id+" ");
        return "redirect:/raport/pokazWszystkie";
    }
    @GetMapping("/raport/createFromFile/{id}")
    public String createRaportFromFile(Authentication authentication, @PathVariable String id, RedirectAttributes atts) {

        MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();

        Raport raport = reportService.createRaport(id,principal.getUsername());
        if (raport != null) {
            atts.addFlashAttribute("successMsg", "Poprawnie utworzono raport o id: " + id +" ");
        } else {
            atts.addFlashAttribute("errorMsg", "Błąd! Nie utworzono raportu o id: " + id + " ");
        }

        return "redirect:/pliki/pokazWszystkie";
    }
    @GetMapping("/raport/createFileFromRaport/{id}")
    public String createFileFromRaport(Authentication authentication, @PathVariable long id, RedirectAttributes atts) {

        MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
        String name =  reportService.getRaportById(id).getNazwa();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        User user = userService.findByUsername(principal.getUsername());
        byte[] file = reportService.createFileFromRaport(id,principal.getUsername());
        FileDB fileDB = storageService.store(new FileDB("Wygenerowany "+ name +" " + LocalDateTime.now().format(formatter),"text/xml",file,user));
        if (fileDB != null) {
            atts.addFlashAttribute("successMsg", "Poprawnie utworzono plik z Bazy Danych o id: " + id +" ");
        } else {
            atts.addFlashAttribute("errorMsg", "Błąd! Nie utworzono Bazy Danych o id: " + id + " ");
        }

        return "redirect:/pliki/pokazWszystkie";
    }
    @GetMapping("/raport/createFileFromRaportExtra/{id}")
    public String createFileFromRaportExtra(Authentication authentication, @PathVariable long id, RedirectAttributes atts) {

        MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
        User user = userService.findByUsername(principal.getUsername());
        byte[] file = reportService.createFileFromRaportExtra(id,principal.getUsername());
        FileDB fileDB = storageService.store(new FileDB("RaportZdbaExtra","text/xml",file,user));
        if (fileDB != null) {
            atts.addFlashAttribute("successMsg", "Poprawnie utworzono plik z Bazy Danych o id: " + id +" ");
        } else {
            atts.addFlashAttribute("errorMsg", "Błąd! Nie utworzono Bazy Danych o id: " + id + " ");
        }

        return "redirect:/pliki/pokazWszystkie";
    }
}
