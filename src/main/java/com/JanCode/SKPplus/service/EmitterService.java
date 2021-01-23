package com.JanCode.SKPplus.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
@Service
public class EmitterService {
    //public List<SseEmitter> emitters;
    public Map<Long,SseEmitter> emitters;
    public EmitterService() {
        this.emitters  = new HashMap<>();
    }

    public Map<Long,SseEmitter> getEmitters() {
        return emitters;
    }

    public void setEmitters(Map<Long,SseEmitter> emitters) {
        this.emitters = emitters;
    }
    public void sendInitEvent(SseEmitter sseEmitter) {
        try {
            sseEmitter.send(SseEmitter.event().name("INIT"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
