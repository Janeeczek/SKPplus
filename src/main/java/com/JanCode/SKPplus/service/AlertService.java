package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.Alert;
import com.JanCode.SKPplus.model.IconType;

import java.util.List;

public interface AlertService {
    List<Alert> sendToAll(String title, String text, IconType iconType);

}
