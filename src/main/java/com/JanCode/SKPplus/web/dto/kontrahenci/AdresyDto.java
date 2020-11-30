package com.JanCode.SKPplus.web.dto.kontrahenci;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Set;
@XmlRootElement(name = "ADRESY")
@XmlType(propOrder={"ADRES"})
public class AdresyDto {
    private List<AdresDto> ADRES;

    public AdresyDto() {
    }

    public AdresyDto(List<AdresDto> ADRES) {
        this.ADRES = ADRES;
    }
    @XmlElement(name="ADRES")
    public List<AdresDto> getADRES() {
        return ADRES;
    }

    public void setADRES(List<AdresDto> ADRES) {
        this.ADRES = ADRES;
    }
}
