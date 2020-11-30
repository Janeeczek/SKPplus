package com.JanCode.SKPplus.web.dto.rejestrySprzedazy;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "REJESTRY_SPRZEDAZY_VAT")
@XmlType(propOrder={ "WERSJA","BAZA_ZRD_ID","BAZA_DOC_ID","REJESTR_SPRZEDAZY_VAT"})
public class RejestrySprzedazyVatDto {
    private double WERSJA;
    private String BAZA_ZRD_ID;
    private String BAZA_DOC_ID;
    private List<RejestrSprzedazyVatDto> REJESTR_SPRZEDAZY_VAT;

    public RejestrySprzedazyVatDto() {
    }

    public RejestrySprzedazyVatDto(double WERSJA, String BAZA_ZRD_ID, String BAZA_DOC_ID, List<RejestrSprzedazyVatDto> REJESTR_SPRZEDAZY_VAT) {
        this.WERSJA = WERSJA;
        this.BAZA_ZRD_ID = BAZA_ZRD_ID;
        this.BAZA_DOC_ID = BAZA_DOC_ID;
        this.REJESTR_SPRZEDAZY_VAT = REJESTR_SPRZEDAZY_VAT;
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
    @XmlElement(name="REJESTR_SPRZEDAZY_VAT")
    public List<RejestrSprzedazyVatDto> getREJESTR_SPRZEDAZY_VAT() {
        return REJESTR_SPRZEDAZY_VAT;
    }

    public void setREJESTR_SPRZEDAZY_VAT(List<RejestrSprzedazyVatDto> REJESTR_SPRZEDAZY_VAT) {
        this.REJESTR_SPRZEDAZY_VAT = REJESTR_SPRZEDAZY_VAT;
    }
}
