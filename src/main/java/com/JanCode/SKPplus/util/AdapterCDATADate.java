package com.JanCode.SKPplus.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class AdapterCDATADate extends XmlAdapter<String, LocalDate> {


    @Override
    public LocalDate unmarshal(String arg0) throws Exception {
        if( arg0 == null || arg0.isEmpty() ) {
            return null;
        }
        else
            return LocalDate.parse(arg0);

    }
    @Override
    public String marshal(LocalDate arg0) throws Exception {
        if(arg0 != null)
            return "<![CDATA[" + arg0.toString() + "]]>";
        else
            return "<![CDATA[]]>";
    }

}
