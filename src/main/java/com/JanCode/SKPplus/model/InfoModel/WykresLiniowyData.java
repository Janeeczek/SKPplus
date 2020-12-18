package com.JanCode.SKPplus.model.InfoModel;

import java.util.List;

public class WykresLiniowyData {
    List<Double> income;

    public WykresLiniowyData(List<Double> income) {
        this.income = income;
    }

    public List<Double> getIncome() {
        return income;
    }

    public void setIncome(List<Double> income) {
        this.income = income;
    }
}
