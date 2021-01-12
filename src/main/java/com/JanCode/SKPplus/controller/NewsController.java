package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.model.IconType;
import com.JanCode.SKPplus.service.AlertService;
import com.JanCode.SKPplus.service.EmitterService;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
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



    @CrossOrigin
    @RequestMapping(value = "/subscribe",consumes = MediaType.ALL_VALUE)
    public SseEmitter subscribe() {
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        try {
            sseEmitter.send(SseEmitter.event().name("INIT"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sseEmitter.onCompletion(()->emitterService.getEmitters().remove(sseEmitter));
        emitterService.getEmitters().add(sseEmitter);
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
        alertService.sendToAll(title,text,iconType);

    }
}
