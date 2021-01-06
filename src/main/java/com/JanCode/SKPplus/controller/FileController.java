package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.model.FileDB;
import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.service.FileStorageService;
import com.JanCode.SKPplus.web.dto.RaportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.event.MouseEvent;
import java.util.List;

@Controller
public class FileController {
    @Autowired
    private FileStorageService storageService;

    @GetMapping("/pliki/upload")
    public ModelAndView showUploadForm() {
        ModelAndView modelAndView = new ModelAndView("/user/uploadXML");
        modelAndView.addObject("raportDto", new RaportDto());
        return modelAndView;
    }

    @PostMapping("/pliki/upload/post")
    public String getFilePost(RedirectAttributes atts, @ModelAttribute RaportDto raportDto, @RequestParam(value = "action", required = true) String action, Authentication authentication) {
        MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
        if (action.equals("save")) {
            try {
                storageService.store(raportDto, principal.getUsername());
            } catch (Exception e) {
                atts.addFlashAttribute("errorMsg", "Nieprawidłowy rodzaj pliku!");
                return "redirect:/pliki/upload";
            }
            atts.addFlashAttribute("successMsg", "Pomyslnie zapisano plik!");
        } else {
            atts.addFlashAttribute("cancelMsg", "Anulowano wysyłanie pliku!");
        }
        return "redirect:/pliki/upload";

    }

    @GetMapping("/pliki/pokazWszystkie")
    public ModelAndView showPlikiTable() {
        ModelAndView modelAndView = new ModelAndView("/user/pliki");
        List<FileDB> files = storageService.getAllFiles();
        modelAndView.addObject("pliki", files);
        return modelAndView;
    }

    @GetMapping("/pliki/pokaz/{id}")
    public ModelAndView showPlikTable(ModelAndView modelAndView, @PathVariable String id) {
        modelAndView.setViewName("/user/plik");
        FileDB file = storageService.getFile(id);
        modelAndView.addObject("plik", file);
        return modelAndView;
    }

    @GetMapping("/pliki/usun/{id}")
    public String usunPlik(RedirectAttributes atts, @PathVariable String id) {
        storageService.deleteFileById(id);
        atts.addFlashAttribute("successMsg", "Pomyslnie usunięto plik o id: " + id + " ");
        return "redirect:/pliki/pokazWszystkie";
    }

    @GetMapping("/pliki/pobierz/{id}")
    public ResponseEntity<Resource> showUploadForm(@PathVariable String id) {
        FileDB file = storageService.getFile(id);
        HttpHeaders headers = new HttpHeaders(); headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+file.getName()+".xml");
        ByteArrayResource resource = new ByteArrayResource(file.getData());
        return ResponseEntity.ok().headers(headers).contentLength(file.getData().length).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
    }
}