package com.JanCode.SKPplus.web.dto.rejestrySprzedazy;

import com.JanCode.SKPplus.model.raportModel.Platnosc;
import com.JanCode.SKPplus.model.raportModel.Platnosci;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@XmlRootElement(name = "PLATNOSCI")
@XmlType(propOrder={"PLATNOSC"})
public class PlatnosciDto {
    private List<PlatnoscDto> PLATNOSC;

    public PlatnosciDto() { }

    public PlatnosciDto(List<PlatnoscDto> PLATNOSC) {
        this.PLATNOSC = PLATNOSC;
    }

    public PlatnosciDto(Platnosci platnosci) {
        List<Platnosc> platnoscList = new ArrayList<>(platnosci.getPlatnosc());
        List<PlatnoscDto> platnoscDtoList = new ArrayList<>();
        for(int i = 0; i< platnoscList.size();i++) {
            platnoscDtoList.add(new PlatnoscDto(platnoscList.get(i)));
        }
        this.PLATNOSC = platnoscDtoList;
    }
    @XmlElement(name="PLATNOSC")
    public List<PlatnoscDto> getPLATNOSC() {
        return PLATNOSC;
    }

    public void setPLATNOSC(List<PlatnoscDto> PLATNOSC) {
        this.PLATNOSC = PLATNOSC;
    }
}
