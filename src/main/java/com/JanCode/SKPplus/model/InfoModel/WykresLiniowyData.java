package com.JanCode.SKPplus.model.InfoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WykresLiniowyData {
    List<String> ke;
    List<Double> val;

    public WykresLiniowyData(List<String> ke, List<Double> val) {
        this.ke = ke;
        this.val = val;
    }
    public WykresLiniowyData() {
        List<String> kke = new ArrayList<>();
        List<Double> vval = new ArrayList<>();
        for(int i =0;i<12;i++) {
            kke.add(String.valueOf(i));
            vval.add((double)i);
        }


    }

    public List<String> getKe() {
        return ke;
    }

    public void setKe(List<String> ke) {
        this.ke = ke;
    }

    public List<Double> getVal() {
        return val;
    }

    public void setVal(List<Double> val) {
        this.val = val;
    }
}
