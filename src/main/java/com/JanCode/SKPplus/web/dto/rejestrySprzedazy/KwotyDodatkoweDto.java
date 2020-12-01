package com.JanCode.SKPplus.web.dto.rejestrySprzedazy;

import com.JanCode.SKPplus.web.dto.rejestrySprzedazy.PozycjaKdDto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Set;
@XmlRootElement(name = "KWOTY_DODATKOWE")
@XmlType(propOrder={"POZYCJA_KD","OPIS_KD"})
public class KwotyDodatkoweDto {
    private PozycjaKdDto POZYCJA_KD;
    private String OPIS_KD;

    public KwotyDodatkoweDto() {
    }

    public KwotyDodatkoweDto(PozycjaKdDto POZYCJA_KD, String OPIS_KD) {
        this.POZYCJA_KD = POZYCJA_KD;
        this.OPIS_KD = OPIS_KD;
    }
    public PozycjaKdDto getPOZYCJA_KD() {
        return POZYCJA_KD;
    }

    public void setPOZYCJA_KD(PozycjaKdDto POZYCJA_KD) {
        this.POZYCJA_KD = POZYCJA_KD;
    }

    public String getOPIS_KD() {
        return OPIS_KD;
    }

    public void setOPIS_KD(String OPIS_KD) {
        this.OPIS_KD = OPIS_KD;
    }
}