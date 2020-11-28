package com.JanCode.SKPplus.web.dto;

import com.JanCode.SKPplus.web.dto.kontrahenci.KontrahentDto;
import com.JanCode.SKPplus.web.dto.rejestrySprzedazy.RejestrSprzedazyVatDto;

import java.util.List;

public class DaneRaportuDto {
    private List<KontrahentDto> KONTRAHENCI;
    private List<RejestrSprzedazyVatDto> REJESTR_SPRZEDAZY_VAT;

    public DaneRaportuDto(List<KontrahentDto> KONTRAHENCI, List<RejestrSprzedazyVatDto> REJESTR_SPRZEDAZY_VAT) {
        this.KONTRAHENCI = KONTRAHENCI;
        this.REJESTR_SPRZEDAZY_VAT = REJESTR_SPRZEDAZY_VAT;
    }

    public DaneRaportuDto() {
    }

    public List<KontrahentDto> getKONTRAHENCI() {
        return KONTRAHENCI;
    }

    public void setKONTRAHENCI(List<KontrahentDto> KONTRAHENCI) {
        this.KONTRAHENCI = KONTRAHENCI;
    }

    public List<RejestrSprzedazyVatDto> getREJESTR_SPRZEDAZY_VAT() {
        return REJESTR_SPRZEDAZY_VAT;
    }

    public void setREJESTR_SPRZEDAZY_VAT(List<RejestrSprzedazyVatDto> REJESTR_SPRZEDAZY_VAT) {
        this.REJESTR_SPRZEDAZY_VAT = REJESTR_SPRZEDAZY_VAT;
    }
}
