package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.model.*;
import com.JanCode.SKPplus.service.FileStorageService;
import com.JanCode.SKPplus.service.RaportServiceImpl;
import com.JanCode.SKPplus.service.UserService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RaportController {
    @Autowired
    private RaportServiceImpl reportService;
    @Autowired
    private FileStorageService storageService;
    @Autowired
    private UserService userService;
    @GetMapping("/raport/pokazWszystkie")
    public ModelAndView pokazStrone(Authentication authentication) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if (mode == AccountType.KSIEGOWOSC || mode == AccountType.DIAGNOSTYKA || mode == AccountType.ADMIN) {
                modelAndView = new ModelAndView("/user/raporty","mode",mode.name());
                modelAndView.addObject("raporty", reportService.getAllRaports());
                modelAndView.addObject("raportService", reportService);
            } else {
                modelAndView = new ModelAndView("/error","errorMsg","Brak uprawnień!");
            }

        } else {
            modelAndView = new ModelAndView("/error","errorMsg","Brak uprawnień!");
        }
        return modelAndView;
    }
    @GetMapping("/raport/usunRaport/{id}")
    public String usunRaport(@PathVariable long id, RedirectAttributes atts,Authentication authentication) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        Raport raport = reportService.getRaportById(id);
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if (mode == AccountType.KSIEGOWOSC || sourcePrincipal.getId()==raport.getFileDB().getUser().getId() ||  mode == AccountType.ADMIN) {
                reportService.removeById(id);
                storageService.deleteFileById(raport.getFileDB().getId());
                atts.addFlashAttribute("successMsg", "Pomyslnie usunięto raport o nazwie: "+raport.getNazwa()+" ");
            } else {
                atts.addFlashAttribute("errorMsg", "Brak uprawnień, aby usunąć raport o nazwie: "+raport.getNazwa()+" ");
            }
        }else atts.addFlashAttribute("errorMsg", "Brak uprawnień, aby usunąć raport o nazwie: "+raport.getNazwa()+" ");
        return "redirect:/raport/pokazWszystkie";
    }
    //nie używane
    @GetMapping("/raport/createFromFile/{id}")
    public String createRaportFromFile(Authentication authentication, @PathVariable String id, RedirectAttributes atts) {

        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if (mode == AccountType.KSIEGOWOSC || mode == AccountType.ADMIN) {
                Raport raport = reportService.createRaport(id,sourcePrincipal.getUsername());
                if (raport != null) {
                    atts.addFlashAttribute("successMsg", "Poprawnie utworzono raport o id: " + id +" ");
                } else {
                    atts.addFlashAttribute("errorMsg", "Błąd! Nie utworzono raportu o id: " + id + " ");
                }
            } else {
                atts.addFlashAttribute("errorMsg", "Brak uprawnień, aby stworzyć raport o id: "+id+" ");
            }
        } else atts.addFlashAttribute("errorMsg", "Brak uprawnień, aby stworzyć raport o id: "+id+" ");
        return "redirect:/pliki/pokazWszystkie";
    }
    //nie używane (teraz downloadFile)
    @GetMapping("/raport/createFileFromRaport/{id}")
    public String createFileFromRaport(Authentication authentication, @PathVariable long id, RedirectAttributes atts) {

        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if (mode == AccountType.KSIEGOWOSC || mode == AccountType.ADMIN) {
                String name = reportService.getRaportById(id).getNazwa();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
                User user = userService.findByUsername(sourcePrincipal.getUsername());
                byte[] file = reportService.createFileFromRaport(id,sourcePrincipal.getUsername());
                FileDB fileDB = storageService.store(new FileDB("Wygenerowany "+ name +" " + LocalDateTime.now().format(formatter),"text/xml",file,user));
                if (fileDB != null) atts.addFlashAttribute("successMsg", "Poprawnie utworzono plik o nazwie: " + fileDB.getName() +" ");
                    else atts.addFlashAttribute("errorMsg", "Błąd! Nie utworzono pliku o nazwie: " + fileDB.getName()+ " ");
            } else atts.addFlashAttribute("errorMsg", "Brak uprawnień, aby stworzyć plik!");
        } else atts.addFlashAttribute("errorMsg", "Brak uprawnień, aby stworzyć plik!");


        return "redirect:/pliki/pokazWszystkie";
    }
    //nie używane (teraz downloadFileExtra)
    @GetMapping("/raport/createFileFromRaportExtra/{id}")
    public String createFileFromRaportExtra(Authentication authentication, @PathVariable long id, RedirectAttributes atts) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();
        if (sourcePrincipal != null) {
            AccountType mode = sourcePrincipal.getAccountType();
            if (mode == AccountType.KSIEGOWOSC || mode == AccountType.ADMIN) {
                String name = reportService.getRaportById(id).getNazwa();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
                User user = userService.findByUsername(sourcePrincipal.getUsername());
                byte[] file = reportService.createFileFromRaportExtra(id,sourcePrincipal.getUsername());
                FileDB fileDB = storageService.store(new FileDB("STACJ Wygenerowany "+ name +" " + LocalDateTime.now().format(formatter),"text/xml",file,user));
                if (fileDB != null) atts.addFlashAttribute("successMsg", "Poprawnie utworzono plik o nazwie: " + fileDB.getName() +" ");
                else atts.addFlashAttribute("errorMsg", "Błąd! Nie utworzono pliku o nazwie: " + fileDB.getName()+ " ");
            } else atts.addFlashAttribute("errorMsg", "Brak uprawnień, aby stworzyć plik!");
        } else atts.addFlashAttribute("errorMsg", "Brak uprawnień, aby stworzyć plik!");
        return "redirect:/pliki/pokazWszystkie";
    }

    @GetMapping("/raport/downloadFileFromRaport/{id}")
    public ResponseEntity<Resource> downloadFile(RedirectAttributes atts, @PathVariable long id, Authentication authentication) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();

        String name = reportService.getRaportById(id).getNazwa();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        User user = userService.findByUsername(sourcePrincipal.getUsername());
        byte[] file = reportService.createFileFromRaport(id,sourcePrincipal.getUsername());
        FileDB fileDB = storageService.store(new FileDB("Wygenerowany "+ name +" " + LocalDateTime.now().format(formatter),"text/xml",file,user));


        if (sourcePrincipal == null) {
            atts.addFlashAttribute("errorMsg", "Brak uprawnień!");
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "redirect:/raport/pokazWszystkie").build();
        }
        if (fileDB == null) {
            atts.addFlashAttribute("errorMsg", "Nie można pobrać raportu! Błąd podczas składania!");
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "redirect:/raport/pokazWszystkie").build();
        }
        AccountType mode = sourcePrincipal.getAccountType();
        if(mode == AccountType.ADMIN || mode == AccountType.KSIEGOWOSC) {

            HttpHeaders headers = new HttpHeaders(); headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileDB.getName()+".xml");
            ByteArrayResource resource = new ByteArrayResource(fileDB.getData());
            atts.addFlashAttribute("successMsg", "Raport jest gotowy do pobrania!");
            return ResponseEntity.ok().headers(headers).contentLength(fileDB.getData().length).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
        }else {
            atts.addFlashAttribute("errorMsg", "Brak uprawnień, aby pobrać raport o nazwie: " + fileDB.getName() + " ");
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("errorMsg","Brak uprawnień, aby pobrać raport o nazwie: " + fileDB.getName() + " " );
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "redirect:/raport/pokazWszystkie").headers(responseHeaders).build();
        }
    }
    @GetMapping("/raport/downloadFileFromRaportExtra/{id}")
    public ResponseEntity<Resource> downloadFileExtra(RedirectAttributes atts, @PathVariable long id, Authentication authentication) {
        MyUserPrincipal sourcePrincipal = (MyUserPrincipal) authentication.getPrincipal();

        String name = reportService.getRaportById(id).getNazwa();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        User user = userService.findByUsername(sourcePrincipal.getUsername());
        byte[] file = reportService.createFileFromRaportExtra(id,sourcePrincipal.getUsername());
        FileDB fileDB = storageService.store(new FileDB("STACJ Wygenerowany "+ name +" " + LocalDateTime.now().format(formatter),"text/xml",file,user));


        if (sourcePrincipal == null) {
            atts.addFlashAttribute("errorMsg", "Brak uprawnień!");
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "redirect:/raport/pokazWszystkie").build();
        }
        if (fileDB == null) {
            atts.addFlashAttribute("errorMsg", "Nie można pobrać raportu! Błąd podczas składania!");
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "redirect:/raport/pokazWszystkie").build();
        }
        AccountType mode = sourcePrincipal.getAccountType();
        if(mode == AccountType.ADMIN || mode == AccountType.KSIEGOWOSC) {

            HttpHeaders headers = new HttpHeaders(); headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileDB.getName()+".xml");
            ByteArrayResource resource = new ByteArrayResource(fileDB.getData());
            atts.addFlashAttribute("successMsg", "Raport jest gotowy do pobrania!");
            return ResponseEntity.ok().headers(headers).contentLength(fileDB.getData().length).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
        }else {
            atts.addFlashAttribute("errorMsg", "Brak uprawnień, aby pobrać raport o nazwie: " + fileDB.getName() + " ");
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("errorMsg","Brak uprawnień, aby pobrać raport o nazwie: " + fileDB.getName() + " " );
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "redirect:/raport/pokazWszystkie").headers(responseHeaders).build();
        }
    }
}
