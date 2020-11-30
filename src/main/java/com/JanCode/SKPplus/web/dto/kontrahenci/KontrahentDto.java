package com.JanCode.SKPplus.web.dto.kontrahenci;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Set;
@XmlRootElement(name = "KONTRAHENT")
@XmlType(propOrder={ "ID_ZRODLA","AKRONIM","FINALNY","ADRESY"})
public class KontrahentDto {
    private int ID_ZRODLA;
    private int AKRONIM;
    private String FINALNY;
    private List<AdresyDto> ADRESY;

    public KontrahentDto() {
    }

    public KontrahentDto(int ID_ZRODLA, int AKRONIM, String FINALNY, List<AdresyDto> ADRESY) {
        this.ID_ZRODLA = ID_ZRODLA;
        this.AKRONIM = AKRONIM;
        this.FINALNY = FINALNY;
        this.ADRESY = ADRESY;
    }

    public int getID_ZRODLA() {
        return ID_ZRODLA;
    }

    public void setID_ZRODLA(int ID_ZRODLA) {
        this.ID_ZRODLA = ID_ZRODLA;
    }

    public int getAKRONIM() {
        return AKRONIM;
    }

    public void setAKRONIM(int AKRONIM) {
        this.AKRONIM = AKRONIM;
    }

    public String getFINALNY() {
        return FINALNY;
    }

    public void setFINALNY(String FINALNY) {
        this.FINALNY = FINALNY;
    }

    @XmlElement(name="ADRESY")
    public List<AdresyDto> getADRESY() {
        return ADRESY;
    }

    public void setADRESY(List<AdresyDto> ADRESY) {
        this.ADRESY = ADRESY;
    }

}
