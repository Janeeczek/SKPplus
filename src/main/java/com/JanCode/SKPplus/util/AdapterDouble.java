package com.JanCode.SKPplus.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;


public class AdapterDouble extends XmlAdapter<String, Double> {


    @Override
    public Double unmarshal(String v) throws Exception {
        if (v == null || v.isEmpty() || v.equals("null")) {
            return null;
        }
        return Double.parseDouble(v);
    }
    @Override
    public String marshal(Double v) throws Exception {
        if (v == null) {
            return null;
        }
        if(v % 1 ==0)
            return String.format("%d",v.longValue());
        else
            return String.format("%s",v);

    }

}