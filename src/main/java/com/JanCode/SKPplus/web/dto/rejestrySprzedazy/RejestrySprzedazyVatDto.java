package com.JanCode.SKPplus.web.dto.rejestrySprzedazy;

import com.JanCode.SKPplus.model.raportModel.RejestrSprzedazyVat;
import com.JanCode.SKPplus.model.raportModel.RejestrySprzedazyVat;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "REJESTRY_SPRZEDAZY_VAT")
@XmlType(propOrder={ "WERSJA","BAZA_ZRD_ID","BAZA_DOC_ID","REJESTR_SPRZEDAZY_VAT"})
public class RejestrySprzedazyVatDto {
    private String WERSJA;
    private String BAZA_ZRD_ID;
    private String BAZA_DOC_ID;
    private List<RejestrSprzedazyVatDto> REJESTR_SPRZEDAZY_VAT;

    public RejestrySprzedazyVatDto() {
    }

    public RejestrySprzedazyVatDto(String WERSJA, String BAZA_ZRD_ID, String BAZA_DOC_ID, List<RejestrSprzedazyVatDto> REJESTR_SPRZEDAZY_VAT) {
        this.WERSJA = WERSJA;
        this.BAZA_ZRD_ID = BAZA_ZRD_ID;
        this.BAZA_DOC_ID = BAZA_DOC_ID;
        this.REJESTR_SPRZEDAZY_VAT = REJESTR_SPRZEDAZY_VAT;
    }
    public RejestrySprzedazyVatDto(RejestrySprzedazyVat rejestrySprzedazyVat) {
        this.WERSJA = rejestrySprzedazyVat.getWersja();
        this.BAZA_ZRD_ID = rejestrySprzedazyVat.getBazaZrdId();
        this.BAZA_DOC_ID = rejestrySprzedazyVat.getBazaDocId();
        List<RejestrSprzedazyVat> rejestrSprzedazyVatList = new ArrayList<>(rejestrySprzedazyVat.getRejestrSprzedazyVat());
        List<RejestrSprzedazyVatDto> rejestrSprzedazyVatDtoList = new ArrayList<>();
        for (int i = 0;i<rejestrSprzedazyVatList.size();i++) {
            rejestrSprzedazyVatDtoList.add(new RejestrSprzedazyVatDto(rejestrSprzedazyVatList.get(i)));
        }
        this.REJESTR_SPRZEDAZY_VAT = rejestrSprzedazyVatDtoList;
    }

    public String getWERSJA() {
        return WERSJA;
    }

    public void setWERSJA(String WERSJA) {
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
