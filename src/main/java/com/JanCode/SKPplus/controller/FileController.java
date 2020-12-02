package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.service.FileStorageService;
import com.JanCode.SKPplus.web.dto.RaportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.event.MouseEvent;

@Controller
public class FileController {
    @Autowired
    private FileStorageService storageService;

    @GetMapping("/raport/upload")
    public ModelAndView showUploadForm(@RequestParam(required = false) String u) {
        ModelAndView modelAndView = new ModelAndView("/user/uploadXML");
        modelAndView.addObject("raportDto",new RaportDto());
        return modelAndView;
    }
    @PostMapping("/raport/upload/post")
    public String getFilePost(RedirectAttributes atts, @ModelAttribute RaportDto raportDto, @RequestParam(value="action", required=true) String action, Authentication authentication) {
        MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
        Model message = null;
        if(action.equals("save")) {
            try {
                storageService.store(raportDto,principal.getUsername());
            } catch (Exception e) {
                System.out.println("BLAD ZAPISU PLIKU");
                atts.addFlashAttribute("errorMsg", "Nieprawidłowy rodzaj pliku!");
                return "redirect:/raport/upload";
            }
            atts.addFlashAttribute("successMsg", "Pomyslnie zapisano plik!");
            return "redirect:/raport/upload";
        } else {
            atts.addFlashAttribute("cancelMsg", "Anulowano wysyłanie pliku!");
            return "redirect:/raport/upload";
        }

    }
}
