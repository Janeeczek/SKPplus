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
    public List<Alert> createNewAlertForAll(String title, String text, IconType iconType) {
        List<Alert> alertList = new ArrayList<>();
        List<User> userList = userService.findAllUsers();
        for (User user : userList) {
            Alert alert = new Alert(title,text,iconType,user);
            alertList.add(alert);
            alertRepository.save(alert);
        }
        return alertList;
    }

    @Override
    public Alert createNewAlertForUser(String title, String text, IconType iconType, User user) {
        Alert alert = new Alert(title,text,iconType,user);
        alertRepository.save(alert);
        return alert;
    }


    @Override
    public void sendAlertsOnSubscribe(User user) {
        List<Alert> alertList = getNotViewedAlertsByUser(user);
        for (int i = alertList.size()-1; i>=0 ;i--) {

            sendAlertToUser(alertList.get(i),"newAlert");
            updateCounter(alertList.get(i).getUser());
        }
    }

    @Override
    public void sendAlertToAll(List<Alert> alertList, String eventName) {
        for( Long user_id : emitterService.getEmitters().keySet()) {

            SseEmitter emitter = emitterService.getEmitters().get(user_id);
            if (emitter != null) {
                for (Alert alert : alertList) {
                    if (alert.getUser().getId() == user_id) {
                        try {
                            emitter.send(SseEmitter.event().name(eventName).data(alertToFormattedData(alert)));
                            updateCounter(alert.getUser());
                        } catch (IOException e) {
                            emitterService.getEmitters().remove(emitter);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void sendAlertToUser(Alert alert, String eventName) {
        SseEmitter emitter = emitterService.getEmitters().get(alert.getUser().getId());
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().name(eventName).data(alertToFormattedData(alert)));
                updateCounter(alert.getUser());
            } catch (IOException e) {
                emitterService.getEmitters().remove(emitter);
            }
        }

    }

    @Override
    public String alertToFormattedData(Alert alert) {
        String eventFormatted = null;
        try {
            eventFormatted = new JSONObject()
                    .put("title",alert.getTitle())
                    .put("date", alert.getCreationDate().format(DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy ")).toString())
                    .put("text",alert.getText())
                    .put("iconTypeName",alert.getIconType().labelName)
                    .put("id",alert.getId())
                    .put("iconTypeBack",alert.getIconType().labelBack).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return eventFormatted;
    }

    @Override
    public List<Alert> getNotViewedAlertsByUser(User user) {
        return alertRepository.getNotViewedAlertsByUserId(user.getId());
    }

    @Override
    public List<Alert> getAllAlertsByUser(User user) {
        return null;
    }

    @Override
    public void updateCounter(User user) {
        String eventFormatted = null;
        try {
            eventFormatted = new JSONObject()
                    .put("counter",alertRepository.getUnviewedCounter(user.getId())).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for( Long user_id : emitterService.getEmitters().keySet()) {

            SseEmitter emitter = emitterService.getEmitters().get(user_id);
            if (emitter != null) {
                try {
                    emitter.send(SseEmitter.event().name("counter").data(eventFormatted));
                } catch (IOException e) {
                    emitterService.getEmitters().remove(emitter);
                }
            }
        }
    }

    @Override
    public Alert getAlertById(Long id) {
        return alertRepository.myFindById(id);
    }

    @Override
    public Alert save(Alert alert) {
        return alertRepository.save(alert);
    }
}
