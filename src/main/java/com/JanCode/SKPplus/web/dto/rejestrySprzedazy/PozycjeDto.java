package com.JanCode.SKPplus.web.dto.rejestrySprzedazy;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "POZYCJE")
@XmlType(propOrder={"POZYCJA"})
public class PozycjeDto {
    private List<PozycjaDto> POZYCJA;

    public PozycjeDto() {
    }

    public PozycjeDto(List<PozycjaDto> POZYCJA) {
        this.POZYCJA = POZYCJA;
    }
    @XmlElement(name="POZYCJA")
    public List<PozycjaDto> getPOZYCJA() {
        return POZYCJA;
    }

    public void setPOZYCJA(List<PozycjaDto> POZYCJA) {
        this.POZYCJA = POZYCJA;
    }
}
