package com.JanCode.SKPplus.web.dto.rejestrySprzedazy;

import com.JanCode.SKPplus.model.raportModel.Pozycja;
import com.JanCode.SKPplus.util.AdapterCDATA;
import com.JanCode.SKPplus.util.AdapterDouble;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "POZYCJA")
@XmlType(propOrder={"STAWKA_VAT","STATUS_VAT","NETTO","VAT","NETTO_SYS","VAT_SYS","NETTO_SYS2","VAT_SYS2"})
public class PozycjaDto {
    private Double STAWKA_VAT;
    private String STATUS_VAT;
    private Double NETTO;
    private Double VAT;
    private Double NETTO_SYS;
    private Double VAT_SYS;
    private Double NETTO_SYS2;
    private Double VAT_SYS2;

    public PozycjaDto() {
    }

    public PozycjaDto(Double STAWKA_VAT, String STATUS_VAT, Double NETTO, Double VAT, Double NETTO_SYS, Double VAT_SYS, Double NETTO_SYS2, Double VAT_SYS2) {
        this.STAWKA_VAT = STAWKA_VAT;
        this.STATUS_VAT = STATUS_VAT;
        this.NETTO = NETTO;
        this.VAT = VAT;
        this.NETTO_SYS = NETTO_SYS;
        this.VAT_SYS = VAT_SYS;
        this.NETTO_SYS2 = NETTO_SYS2;
        this.VAT_SYS2 = VAT_SYS2;
    }

    public PozycjaDto(Pozycja pozycja) {
        this.STAWKA_VAT = pozycja.getStawka_vat();
        this.STATUS_VAT = pozycja.getStatus_vat();
        this.NETTO = pozycja.getNetto();
        this.VAT = pozycja.getVat();
        this.NETTO_SYS = pozycja.getNetto_sys();
        this.VAT_SYS = pozycja.getVat_sys();
        this.NETTO_SYS2 = pozycja.getNetto_sys2();
        this.VAT_SYS2 = pozycja.getVat_sys2();
    }
    @XmlJavaTypeAdapter(AdapterDouble.class)
    public Double getSTAWKA_VAT() {
        return STAWKA_VAT;
    }

    public void setSTAWKA_VAT(Double STAWKA_VAT) {
        this.STAWKA_VAT = STAWKA_VAT;
    }

    public String getSTATUS_VAT() {
        return STATUS_VAT;
    }

    public void setSTATUS_VAT(String STATUS_VAT) {
        this.STATUS_VAT = STATUS_VAT;
    }
    @XmlJavaTypeAdapter(AdapterDouble.class)
    public Double getNETTO() {
        return NETTO;
    }

    public void setNETTO(Double NETTO) {
        this.NETTO = NETTO;
    }
    @XmlJavaTypeAdapter(AdapterDouble.class)
    public Double getVAT() {
        return VAT;
    }

    public void setVAT(Double VAT) {
        this.VAT = VAT;
    }
    @XmlJavaTypeAdapter(AdapterDouble.class)
    public Double getNETTO_SYS() {
        return NETTO_SYS;
    }

    public void setNETTO_SYS(Double NETTO_SYS) {
        this.NETTO_SYS = NETTO_SYS;
    }
    @XmlJavaTypeAdapter(AdapterDouble.class)
    public Double getVAT_SYS() {
        return VAT_SYS;
    }

    public void setVAT_SYS(Double VAT_SYS) {
        this.VAT_SYS = VAT_SYS;
    }
    @XmlJavaTypeAdapter(AdapterDouble.class)
    public Double getNETTO_SYS2() {
        return NETTO_SYS2;
    }

    public void setNETTO_SYS2(Double NETTO_SYS2) {
        this.NETTO_SYS2 = NETTO_SYS2;
    }
    @XmlJavaTypeAdapter(AdapterDouble.class)
    public Double getVAT_SYS2() {
        return VAT_SYS2;
    }

    public void setVAT_SYS2(Double VAT_SYS2) {
        this.VAT_SYS2 = VAT_SYS2;
    }
}
