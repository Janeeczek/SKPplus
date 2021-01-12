package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.Alert;
import com.JanCode.SKPplus.model.IconType;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlertServiceImpl implements AlertService {
    @Autowired
    private EmitterService emitterService;
    @Autowired
    private UserService userService;
    @Autowired
    private AlertRepository alertRepository;
    @Override
    public List<Alert> sendToAll(String title, String text, IconType iconType) {
        List<Alert> alertList = new ArrayList<>();
        List<User> userList = userService.findAllUsers();
        for (User user : userList) {
            Alert alert = new Alert(title,text, iconType,user);
            alertList.add(alert);
            alertRepository.save(alert);
        }
        int counter = 1;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy ");
        String eventFormatted = null;
        try {
            eventFormatted = new JSONObject()
                    .put("title",title)
                    .put("date", now.format(myFormatObj).toString())
                    .put("text",text)
                    .put("iconTypeName",iconType.labelName)
                    .put("counter",counter)
                    .put("iconTypeBack",iconType.labelBack).toString();


        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (SseEmitter emitter : emitterService.getEmitters()) {
            try {
                emitter.send(SseEmitter.event().name("latest").data(eventFormatted));
            } catch (IOException e) {
                emitterService.getEmitters().remove(emitter);
            }
        }
        return alertList;
    }
}
