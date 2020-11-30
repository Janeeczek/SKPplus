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
    private List<PozycjaKdDto> POZYCJA_KD;
    private String OPIS_KD;

    public KwotyDodatkoweDto() {
    }

    public KwotyDodatkoweDto(List<PozycjaKdDto> POZYCJA_KD, String OPIS_KD) {
        this.POZYCJA_KD = POZYCJA_KD;
        this.OPIS_KD = OPIS_KD;
    }
    @XmlElement(name="POZYCJA_KD")
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
