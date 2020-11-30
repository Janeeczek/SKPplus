package com.JanCode.SKPplus.web.dto.rejestrySprzedazy;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Set;
@XmlRootElement(name = "PLATNOSCI")
@XmlType(propOrder={"PLATNOSC"})
public class PlatnosciDto {
    private List<PlatnoscDto> PLATNOSC;

    public PlatnosciDto(List<PlatnoscDto> PLATNOSC) {
        this.PLATNOSC = PLATNOSC;
    }

    public PlatnosciDto() {
    }
    @XmlElement(name="PLATNOSC")
    public List<PlatnoscDto> getPLATNOSC() {
        return PLATNOSC;
    }

    public void setPLATNOSC(List<PlatnoscDto> PLATNOSC) {
        this.PLATNOSC = PLATNOSC;
    }
}
