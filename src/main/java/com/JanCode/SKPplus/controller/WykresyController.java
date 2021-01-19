package com.JanCode.SKPplus.controller;

import com.JanCode.SKPplus.model.InfoModel.WykresKolowyData;
import com.JanCode.SKPplus.model.InfoModel.WykresLiniowyData;
import com.JanCode.SKPplus.model.Raport;
import com.JanCode.SKPplus.service.RaportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WykresyController {

    @Autowired
    private RaportServiceImpl raportService;


}
