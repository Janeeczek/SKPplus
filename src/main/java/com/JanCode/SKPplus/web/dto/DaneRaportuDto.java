package com.JanCode.SKPplus.web.dto;

import com.JanCode.SKPplus.web.dto.kontrahenci.KontrahenciDto;
import com.JanCode.SKPplus.web.dto.kontrahenci.KontrahentDto;
import com.JanCode.SKPplus.web.dto.rejestrySprzedazy.RejestrSprzedazyVatDto;
import com.JanCode.SKPplus.web.dto.rejestrySprzedazy.RejestrySprzedazyVatDto;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Set;
@XmlRootElement(name = "ROOT")
@XmlType(propOrder={"KONTRAHENCI","REJESTRY_SPRZEDAZY_VAT"})

public class DaneRaportuDto {

    private KontrahenciDto KONTRAHENCI;

    private RejestrySprzedazyVatDto REJESTRY_SPRZEDAZY_VAT;

    public DaneRaportuDto() {
    }

    public DaneRaportuDto(KontrahenciDto KONTRAHENCI, RejestrySprzedazyVatDto REJESTRY_SPRZEDAZY_VAT) {
        this.KONTRAHENCI = KONTRAHENCI;
        this.REJESTRY_SPRZEDAZY_VAT = REJESTRY_SPRZEDAZY_VAT;
    }

    public KontrahenciDto getKONTRAHENCI() {
        return KONTRAHENCI;
    }

    public void setKONTRAHENCI(KontrahenciDto KONTRAHENCI) {
        this.KONTRAHENCI = KONTRAHENCI;
    }

    public RejestrySprzedazyVatDto getREJESTRY_SPRZEDAZY_VAT() {
        return REJESTRY_SPRZEDAZY_VAT;
    }

    public void setREJESTRY_SPRZEDAZY_VAT(RejestrySprzedazyVatDto REJESTRY_SPRZEDAZY_VAT) {
        this.REJESTRY_SPRZEDAZY_VAT = REJESTRY_SPRZEDAZY_VAT;
    }
}
