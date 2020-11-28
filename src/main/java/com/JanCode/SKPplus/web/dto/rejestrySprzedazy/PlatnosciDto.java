package com.JanCode.SKPplus.web.dto.rejestrySprzedazy;

import java.util.List;

public class PlatnosciDto {
    private List<PlatnoscDto> PLATNOSC;

    public PlatnosciDto(List<PlatnoscDto> PLATNOSC) {
        this.PLATNOSC = PLATNOSC;
    }

    public PlatnosciDto() {
    }

    public List<PlatnoscDto> getPLATNOSC() {
        return PLATNOSC;
    }

    public void setPLATNOSC(List<PlatnoscDto> PLATNOSC) {
        this.PLATNOSC = PLATNOSC;
    }
}
