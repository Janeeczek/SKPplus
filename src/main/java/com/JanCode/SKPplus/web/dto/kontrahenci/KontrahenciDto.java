package com.JanCode.SKPplus.web.dto.kontrahenci;

import com.JanCode.SKPplus.model.raportModel.Kontrahent;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
@XmlRootElement(name = "KONTRAHENCI")
@XmlType(propOrder={ "WERSJA","BAZA_ZRD_ID","BAZA_DOC_ID","KONTRAHENT"})
public class KontrahenciDto {
    private double WERSJA;
    private String BAZA_ZRD_ID;
    private String BAZA_DOC_ID;
    private List<KontrahentDto> KONTRAHENT;

    public KontrahenciDto() {
    }

    public KontrahenciDto(double WERSJA, String BAZA_ZRD_ID, String BAZA_DOC_ID, List<KontrahentDto> KONTRAHENT) {
        this.WERSJA = WERSJA;
        this.BAZA_ZRD_ID = BAZA_ZRD_ID;
        this.BAZA_DOC_ID = BAZA_DOC_ID;
        this.KONTRAHENT = KONTRAHENT;
    }

    public double getWERSJA() {
        return WERSJA;
    }

    public void setWERSJA(double WERSJA) {
        this.WERSJA = WERSJA;
    }

    public String getBAZA_ZRD_ID() {
        return BAZA_ZRD_ID;
    }

    public void setBAZA_ZRD_ID(String BAZA_ZRD_ID) {
        this.BAZA_ZRD_ID = BAZA_ZRD_ID;
    }

    public String getBAZA_DOC_ID() {
        return BAZA_DOC_ID;
    }

    public void setBAZA_DOC_ID(String BAZA_DOC_ID) {
        this.BAZA_DOC_ID = BAZA_DOC_ID;
    }
    @XmlElement(name="KONTRAHENT")
    public List<KontrahentDto> getKONTRAHENT() {
        return KONTRAHENT;
    }

    public void setKONTRAHENT(List<KontrahentDto> KONTRAHENT) {
        this.KONTRAHENT = KONTRAHENT;
    }
}
