package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.model.*;
import com.JanCode.SKPplus.service.FileStorageService;
import com.JanCode.SKPplus.service.RaportServiceImpl;
import com.JanCode.SKPplus.web.dto.RaportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FileController {
    @Autowired
    private FileStorageService storageService;
    @Autowired
    private RaportServiceImpl reportService;

    @GetMapping("/pliki/upload")
    public ModelAndView showUploadForm(Authentication authentication) {

        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            System.err.println(mode);
            if (mode == AccountType.KSIEGOWOSC || mode == AccountType.DIAGNOSTYKA || mode == AccountType.ADMIN) {
                modelAndView = new ModelAndView("user/uploadXML","mode",mode.name());
                modelAndView.addObject("raportDto", new RaportDto());
            } else {
                modelAndView = new ModelAndView("/error","errorMsg","Brak uprawnień!");
            }
        } else modelAndView = new ModelAndView("/error","errorMsg","Brak uprawnień!");
        return modelAndView;

    }
    //zapisanie pliku i utworzenie raportu
    @PostMapping("/pliki/upload/post")
    public String getFilePost(RedirectAttributes atts, @ModelAttribute RaportDto raportDto, @RequestParam(value = "action", required = true) String action, Authentication authentication) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if (mode == AccountType.KSIEGOWOSC || mode == AccountType.DIAGNOSTYKA || mode == AccountType.ADMIN) {
                if (action.equals("save")) {
                    FileDB file =null;
                    try {
                        file = storageService.store(raportDto, sourcePrincipal.getUsername());
                    } catch (Exception e) {
                        atts.addFlashAttribute("errorMsg", "Nieprawidłowy rodzaj pliku!");
                        return "redirect:/pliki/upload";
                    }
                    if (file == null) {
                        atts.addFlashAttribute("errorMsg", "Błąd podczas wysyłania pliku!!");
                    }
                    //atts.addFlashAttribute("successMsg", "Pomyslnie zapisano plik!");
                    //poprawnie zapisano
                    Raport raport = reportService.createRaport(file.getId(),sourcePrincipal.getUsername());
                    if (raport != null) {
                        atts.addFlashAttribute("successMsg", "Poprawnie utworzono raport o nazwie: " + raportDto.getName() +" ");
                        return "redirect:/raport/pokazWszystkie";
                    } else {
                        atts.addFlashAttribute("errorMsg", "Błąd! Nie utworzono raportu o nazwie: " + raportDto.getName() + " ");
                    }


                } else {
                    atts.addFlashAttribute("cancelMsg", "Anulowano wysyłanie pliku!");
                }
                return "redirect:/pliki/upload";

            } else {
                atts.addFlashAttribute("errorMsg", "Brak uprawnień, aby wysłać plik");
            }
        }else atts.addFlashAttribute("errorMsg", "Brak uprawnień, aby wysłać plik");
        return "redirect:/pliki/upload";

    }
    //nie używane
    @GetMapping("/pliki/pokazWszystkie")
    public ModelAndView showPlikiTable(Authentication authentication) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            System.err.println(mode);
            if (mode == AccountType.KSIEGOWOSC ||mode == AccountType.ADMIN|| mode == AccountType.DIAGNOSTYKA) {
                modelAndView = new ModelAndView("user/pliki","mode",mode.name());
                List<FileDB> files = storageService.getAllFiles();
                modelAndView.addObject("pliki", files);
            } else {
                modelAndView = new ModelAndView("/error","errorMsg","Brak uprawnień!");
            }
        } else modelAndView = new ModelAndView("/error","errorMsg","Brak uprawnień!");
        return modelAndView;
    }
    //nie używane
    @GetMapping("/pliki/pokaz/{id}")
    public ModelAndView showPlikTable(ModelAndView modelAndView, @PathVariable String id) {
        modelAndView.setViewName("user/plik");
        FileDB file = storageService.getFile(id);
        modelAndView.addObject("plik", file);
        return modelAndView;
    }
    //używane w raportController
    @GetMapping("/pliki/usun/{id}")
    public ModelAndView usunPlik(RedirectAttributes atts, @PathVariable String id,Authentication authentication) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView;
        FileDB fileDB = storageService.getFile(id);
        if (sourcePrincipal == null) {
            modelAndView = new ModelAndView("/error","errorMsg","Brak uprawnień!");
            return modelAndView;
        }
        if (fileDB == null) {
            modelAndView = new ModelAndView("/error","errorMsg","Nie ma takiego pliku do usunięcia!");
            return modelAndView;
        }
        AccountType mode = sourcePrincipal.getAccountType();
        if( mode == AccountType.ADMIN || sourcePrincipal.getId()==fileDB.getUser().getId() || mode == AccountType.KSIEGOWOSC) {
            storageService.deleteFileById(id);
            atts.addFlashAttribute("successMsg", "Pomyslnie usunięto plik o id: " + id + " ");
            modelAndView = new ModelAndView("redirect:/pliki/pokazWszystkie");
            return modelAndView;
        }else {
            modelAndView = new ModelAndView("redirect:/pliki/pokazWszystkie");
            atts.addFlashAttribute("errorMsg", "Brak uprawnień, aby usunąć plik o id: " + id + " ");
            return modelAndView;
        }
    }

    @GetMapping("/pliki/pobierz/{id}")
    public ResponseEntity<Resource> showUploadForm(RedirectAttributes atts,@PathVariable String id,Authentication authentication) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        FileDB fileDB = storageService.getFile(id);

        if (sourcePrincipal == null) {
            atts.addFlashAttribute("errorMsg", "Brak uprawnień!");
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "redirect:/pliki/pokazWszystkie").build();
        }
        if (fileDB == null) {
            atts.addFlashAttribute("errorMsg", "Nie ma takiego pliku do usunięcia!");
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "redirect:/pliki/pokazWszystkie").build();
        }
        AccountType mode = sourcePrincipal.getAccountType();
        if(mode == AccountType.ADMIN || mode == AccountType.KSIEGOWOSC) {
            HttpHeaders headers = new HttpHeaders(); headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileDB.getName()+".xml");
            ByteArrayResource resource = new ByteArrayResource(fileDB.getData());
            atts.addFlashAttribute("successMsg", "Plik jest gotowy do pobrania!");
            return ResponseEntity.ok().headers(headers).contentLength(fileDB.getData().length).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
        }else {
            atts.addFlashAttribute("errorMsg", "Brak uprawnień, aby pobrać plik o id: " + id + " ");
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("errorMsg","Brak uprawnień, aby pobrać plik o id: " + id + " " );
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "redirect:/pliki/pokazWszystkie").headers(responseHeaders).build();
        }
    }
}