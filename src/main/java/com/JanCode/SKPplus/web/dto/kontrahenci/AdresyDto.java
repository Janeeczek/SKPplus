package com.JanCode.SKPplus.web.dto.kontrahenci;

import java.util.List;

public class AdresyDto {
    private List<AdresDto> ADRES;

    public AdresyDto() {
    }

    public AdresyDto(List<AdresDto> ADRES) {
        this.ADRES = ADRES;
    }

    public List<AdresDto> getADRES() {
        return ADRES;
    }

    public void setADRES(List<AdresDto> ADRES) {
        this.ADRES = ADRES;
    }
}
