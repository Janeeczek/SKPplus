package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.Raport;

import java.util.List;

public interface RaportService {
    Raport createRaport();
    Raport getRaportById();
    Raport saveRaport();

    void removeById(long id);
    void removeAll();
    double getAllIncome();

    List<Raport> getAllRaports();

    List<Double> getAllIncomeList();
}
