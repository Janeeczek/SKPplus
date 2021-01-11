package com.JanCode.SKPplus.controller;

import netscape.javascript.JSObject;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@RestController
public class NewsController {

    public List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @CrossOrigin
    @RequestMapping(value = "/subscribe",consumes = MediaType.ALL_VALUE)
    public SseEmitter subscribe() {
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        try {
            sseEmitter.send(SseEmitter.event().name("INIT"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sseEmitter.onCompletion(()->emitters.remove(sseEmitter));
        emitters.add(sseEmitter);
        return sseEmitter;
    }
    @PostMapping(value = "/dispatchEvent")
    public void dispatchEventsToClients (@RequestParam String title, @RequestParam String text) {
        System.out.println("WYSYLAM!");
        System.out.println("Title: " + title);
        System.out.println("Text: " + text);
        String eventFormatted = null;
        try {
            eventFormatted = new JSONObject()
                    .put("title",title)
                    .put("text",text).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event().name("latest").data(eventFormatted));
            } catch (IOException e) {
                emitters.remove(emitter);
            }
        }

    }
}
