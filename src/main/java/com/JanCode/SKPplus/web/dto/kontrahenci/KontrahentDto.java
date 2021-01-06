package com.JanCode.SKPplus.web.dto.kontrahenci;


import com.JanCode.SKPplus.model.raportModel.Adres;
import com.JanCode.SKPplus.model.raportModel.Kontrahent;
import com.JanCode.SKPplus.util.AdapterCDATA;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@XmlRootElement(name = "KONTRAHENT")
@XmlType(propOrder={ "ID_ZRODLA","AKRONIM","FINALNY","ADRESY"})
public class KontrahentDto {

    private String ID_ZRODLA;
    private String AKRONIM;
    private String FINALNY;
    private AdresyDto ADRESY;

    public KontrahentDto() {
    }

    public KontrahentDto(String ID_ZRODLA, String AKRONIM, String FINALNY, AdresyDto ADRESY) {
        this.ID_ZRODLA = ID_ZRODLA;
        this.AKRONIM = AKRONIM;
        this.FINALNY = FINALNY;
        this.ADRESY = ADRESY;
    }
    public KontrahentDto(Kontrahent kontrahent) {
        this.ID_ZRODLA = kontrahent.getZrodloId();
        this.AKRONIM = kontrahent.getAkronim();
        this.FINALNY = kontrahent.getFinalny();
        this.ADRESY = new AdresyDto(kontrahent.getAdresy());
    }
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    public String getID_ZRODLA() {
        return ID_ZRODLA;
    }

    public void setID_ZRODLA(String ID_ZRODLA) {
        this.ID_ZRODLA = ID_ZRODLA;
    }
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    public String getAKRONIM() {
        return AKRONIM;
    }

    public void setAKRONIM(String AKRONIM) {
        this.AKRONIM = AKRONIM;
    }

    public String getFINALNY() {
        return FINALNY;
    }

    public void setFINALNY(String FINALNY) {
        this.FINALNY = FINALNY;
    }

    public AdresyDto getADRESY() {
        return ADRESY;
    }

    public void setADRESY(AdresyDto ADRESY) {
        this.ADRESY = ADRESY;
    }

}
