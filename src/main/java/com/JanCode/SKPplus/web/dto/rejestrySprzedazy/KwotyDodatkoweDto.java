package com.JanCode.SKPplus.web.dto.rejestrySprzedazy;

import com.JanCode.SKPplus.web.dto.rejestrySprzedazy.PozycjaKdDto;

import java.util.List;

public class KwotyDodatkoweDto {
    private List<PozycjaKdDto> POZYCJA_KD;
    private String OPIS_KD;

    public KwotyDodatkoweDto() {
    }

    public KwotyDodatkoweDto(List<PozycjaKdDto> POZYCJA_KD, String OPIS_KD) {
        this.POZYCJA_KD = POZYCJA_KD;
        this.OPIS_KD = OPIS_KD;
    }

    public List<PozycjaKdDto> getPOZYCJA_KD() {
        return POZYCJA_KD;
    }

    public void setPOZYCJA_KD(List<PozycjaKdDto> POZYCJA_KD) {
        this.POZYCJA_KD = POZYCJA_KD;
    }

    public String getOPIS_KD() {
        return OPIS_KD;
    }

    public void setOPIS_KD(String OPIS_KD) {
        this.OPIS_KD = OPIS_KD;
    }
}
