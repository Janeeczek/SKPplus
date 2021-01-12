package com.JanCode.SKPplus.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
@Service
public class EmitterService {
    public List<SseEmitter> emitters;

    public EmitterService() {
        this.emitters  = new CopyOnWriteArrayList<>();
    }

    public List<SseEmitter> getEmitters() {
        return emitters;
    }

    public void setEmitters(List<SseEmitter> emitters) {
        this.emitters = emitters;
    }
}
