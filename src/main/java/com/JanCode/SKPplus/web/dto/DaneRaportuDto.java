package com.JanCode.SKPplus.web.dto;

import com.JanCode.SKPplus.model.Raport;
import com.JanCode.SKPplus.model.raportModel.Kontrahent;
import com.JanCode.SKPplus.web.dto.kontrahenci.KontrahenciDto;
import com.JanCode.SKPplus.web.dto.kontrahenci.KontrahentDto;
import com.JanCode.SKPplus.web.dto.rejestrySprzedazy.RejestrSprzedazyVatDto;
import com.JanCode.SKPplus.web.dto.rejestrySprzedazy.RejestrySprzedazyVatDto;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "ROOT")
@XmlType(propOrder={"KONTRAHENCI","REJESTRY_SPRZEDAZY_VAT"})
public class DaneRaportuDto {
    @XmlAttribute(name="xmlns")
    String xmlns; //= "http://www.comarch.pl/cdn/optima/offline";
    private KontrahenciDto KONTRAHENCI;

    private RejestrySprzedazyVatDto REJESTRY_SPRZEDAZY_VAT;

    public DaneRaportuDto() {
    }

    public DaneRaportuDto(KontrahenciDto KONTRAHENCI, RejestrySprzedazyVatDto REJESTRY_SPRZEDAZY_VAT) {
        this.KONTRAHENCI = KONTRAHENCI;
        this.REJESTRY_SPRZEDAZY_VAT = REJESTRY_SPRZEDAZY_VAT;
    }
    public DaneRaportuDto(Raport raport) {
       this.xmlns = "http://www.comarch.pl/cdn/optima/offline";
        this.KONTRAHENCI = new KontrahenciDto(raport.getKontrahenci());
        this.REJESTRY_SPRZEDAZY_VAT = new RejestrySprzedazyVatDto(raport.getRejestrySprzedazyVat());

    }
    @XmlElement(name="KONTRAHENCI")
    public KontrahenciDto getKONTRAHENCI() {
        return KONTRAHENCI;
    }

    public void setKONTRAHENCI(KontrahenciDto KONTRAHENCI) {
        this.KONTRAHENCI = KONTRAHENCI;
    }
    @XmlElement(name="REJESTRY_SPRZEDAZY_VAT")
    public RejestrySprzedazyVatDto getREJESTRY_SPRZEDAZY_VAT() {
        return REJESTRY_SPRZEDAZY_VAT;
    }

    public void setREJESTRY_SPRZEDAZY_VAT(RejestrySprzedazyVatDto REJESTRY_SPRZEDAZY_VAT) {
        this.REJESTRY_SPRZEDAZY_VAT = REJESTRY_SPRZEDAZY_VAT;
    }
}
