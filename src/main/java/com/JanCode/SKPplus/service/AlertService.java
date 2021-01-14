package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.Alert;
import com.JanCode.SKPplus.model.IconType;
import com.JanCode.SKPplus.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface AlertService {
    List<Alert> createNewAlertForAll(String title, String text, IconType iconType);
    Alert createNewAlertForUser(String title, String text, IconType iconType, User user);
    void sendAlertsOnSubscribe(User user);
    void sendAlertToAll(List<Alert> alertList, String eventName);
    void sendAlertToUser(Alert alert, String eventName);
    String alertToFormattedData(Alert alert);
    List<Alert> getNotViewedAlertsByUser(User user);
    List<Alert> getAllAlertsByUser(User user);
    void updateCounter(User user);
    Alert getAlertById(Long id);
    Alert save(Alert alert);

}
