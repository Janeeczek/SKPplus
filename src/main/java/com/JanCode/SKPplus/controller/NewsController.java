package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.model.Alert;
import com.JanCode.SKPplus.model.IconType;
import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.service.AlertService;
import com.JanCode.SKPplus.service.EmitterService;
import com.JanCode.SKPplus.service.UserService;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@RestController
public class NewsController {

    @Autowired
    private AlertService alertService;

    @Autowired
    private EmitterService emitterService;

    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/subscribe",consumes = MediaType.ALL_VALUE)
    public SseEmitter subscribe(Authentication authentication) {
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        if (authentication != null) {
            MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
            emitterService.sendInitEvent(sseEmitter);
            emitterService.getEmitters().put(principal.getId(),sseEmitter);
            sseEmitter.onCompletion(()->emitterService.getEmitters().remove(sseEmitter));
            sseEmitter.onError((e)->emitterService.getEmitters().remove(sseEmitter));
            sseEmitter.onTimeout(()->emitterService.getEmitters().remove(sseEmitter));
            alertService.sendAlertsOnSubscribe(userService.findByUsername(principal.getUsername()));
        }

        return sseEmitter;
    }
    @PostMapping(value = "/dispatchEvent")
    public void dispatchEventsToClients (@RequestParam String title, @RequestParam String text,@RequestParam String icon) {
        IconType iconType;
        if( icon.equals("FILE")) {
            iconType = IconType.FILE;
        }
        else if( icon.equals("DONATE")) {
            iconType = IconType.DONATE;
        }
        else {
            iconType = IconType.EXCLAMATION_TRIANGLE;
        }
        System.out.println(iconType);
        List<Alert> alertList =  alertService.createNewAlertForAll(title,text,iconType);
        alertService.sendAlertToAll(alertList,"newAlert");
    }
    @PostMapping(value = "/updateViewed")
    public void updateViewed (@RequestParam Long id) {
        Alert alert = alertService.getAlertById(id);
        if (alert != null) {
            alert.setViewed(true);
            alertService.save(alert);
            alertService.updateCounter(alert.getUser());
        } else {
            System.out.println("updateViewed: nie znaleziono alertu!");
        }

    }
}
