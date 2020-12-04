package com.JanCode.SKPplus.web.dto.kontrahenci;

import com.JanCode.SKPplus.model.raportModel.Kontrahenci;
import com.JanCode.SKPplus.model.raportModel.Kontrahent;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement(name = "KONTRAHENCI")
@XmlType(propOrder={ "WERSJA","BAZA_ZRD_ID","BAZA_DOC_ID","KONTRAHENT"})
public class KontrahenciDto {
    private String WERSJA;
    private String BAZA_ZRD_ID;
    private String BAZA_DOC_ID;
    private List<KontrahentDto> KONTRAHENT;

    public KontrahenciDto() {
    }

    public KontrahenciDto(String WERSJA, String BAZA_ZRD_ID, String BAZA_DOC_ID, List<KontrahentDto> KONTRAHENT) {
        this.WERSJA = WERSJA;
        this.BAZA_ZRD_ID = BAZA_ZRD_ID;
        this.BAZA_DOC_ID = BAZA_DOC_ID;
        this.KONTRAHENT = KONTRAHENT;
    }
    public KontrahenciDto(Kontrahenci kontrahenci) {
        this.WERSJA = kontrahenci.getWersja();
        this.BAZA_DOC_ID = kontrahenci.getBazaDocId();
        this.BAZA_ZRD_ID = kontrahenci.getBazaZrdId();
        List<Kontrahent> konList =new ArrayList<>( kontrahenci.getKontrahent());
        List<KontrahentDto>  konDtoList= new ArrayList<>();
        for (int i = 0; i < konList.size(); i++) {
            konDtoList.add(new KontrahentDto(konList.get(i)));
        }
        this.KONTRAHENT = konDtoList;
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
    @XmlElement(name="KONTRAHENT")
    public List<KontrahentDto> getKONTRAHENT() {
        return KONTRAHENT;
    }

    public void setKONTRAHENT(List<KontrahentDto> KONTRAHENT) {
        this.KONTRAHENT = KONTRAHENT;
    }
}
