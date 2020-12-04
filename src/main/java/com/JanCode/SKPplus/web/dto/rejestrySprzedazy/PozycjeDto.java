package com.JanCode.SKPplus.web.dto.rejestrySprzedazy;

import com.JanCode.SKPplus.model.raportModel.Pozycja;
import com.JanCode.SKPplus.model.raportModel.Pozycje;
import com.JanCode.SKPplus.model.raportModel.RejestrSprzedazyVat;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "POZYCJE")
@XmlType(propOrder={"POZYCJA"})
public class PozycjeDto {
    private List<PozycjaDto> POZYCJA;

    public PozycjeDto() { }

    public PozycjeDto(List<PozycjaDto> POZYCJA) {
        this.POZYCJA = POZYCJA;
    }

    public PozycjeDto(Pozycje pozycje) {
        List<Pozycja> pozycjaList = new ArrayList<>(pozycje.getPozycja());
        List<PozycjaDto> pozycjaDtoList = new ArrayList<>();
        for (int i = 0;i<pozycjaList.size();i++) {
            pozycjaDtoList.add(new PozycjaDto(pozycjaList.get(i)));
        }
        this.POZYCJA = pozycjaDtoList;
    }

    @XmlElement(name="POZYCJA")
    public List<PozycjaDto> getPOZYCJA() {
        return POZYCJA;
    }

    public void setPOZYCJA(List<PozycjaDto> POZYCJA) {
        this.POZYCJA = POZYCJA;
    }
}
