package com.JanCode.SKPplus.web.dto.kontrahenci;

import com.JanCode.SKPplus.model.raportModel.Adres;
import com.JanCode.SKPplus.model.raportModel.Adresy;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@XmlRootElement(name = "ADRESY")
@XmlType(propOrder={"ADRES"})
public class AdresyDto {
    private List<AdresDto> ADRES;

    public AdresyDto() { }
    public AdresyDto(List<AdresDto> ADRES) {
        this.ADRES = ADRES;
    }

    public AdresyDto(Adresy adresy) {
        List<Adres> adresList = new ArrayList<>(adresy.getAdres());
        List<AdresDto> adresDtoList = new ArrayList<>();
        for (int i = 0;i< adresList.size();i++) {
            adresDtoList.add(new AdresDto(adresList.get(i)));
        }
        this.ADRES = adresDtoList;
    }

    @XmlElement(name="ADRES")
    public List<AdresDto> getADRES() {
        return ADRES;
    }

    public void setADRES(List<AdresDto> ADRES) {
        this.ADRES = ADRES;
    }
}
