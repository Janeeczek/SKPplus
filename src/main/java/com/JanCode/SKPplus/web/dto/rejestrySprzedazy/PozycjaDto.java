package com.JanCode.SKPplus.web.dto.rejestrySprzedazy;

public class PozycjaDto {
    private double STAWKA_VAT;
    private String STATUS_VAT;
    private double NETTO;
    private double VAT;
    private double NETTO_SYS;
    private double VAT_SYS;
    private double NETTO_SYS2;
    private double VAT_SYS2;

    public PozycjaDto(double STAWKA_VAT, String STATUS_VAT, double NETTO, double VAT, double NETTO_SYS, double VAT_SYS, double NETTO_SYS2, double VAT_SYS2) {
        this.STAWKA_VAT = STAWKA_VAT;
        this.STATUS_VAT = STATUS_VAT;
        this.NETTO = NETTO;
        this.VAT = VAT;
        this.NETTO_SYS = NETTO_SYS;
        this.VAT_SYS = VAT_SYS;
        this.NETTO_SYS2 = NETTO_SYS2;
        this.VAT_SYS2 = VAT_SYS2;
    }

    public PozycjaDto() {
    }

    public double getSTAWKA_VAT() {
        return STAWKA_VAT;
    }

    public void setSTAWKA_VAT(double STAWKA_VAT) {
        this.STAWKA_VAT = STAWKA_VAT;
    }

    public String getSTATUS_VAT() {
        return STATUS_VAT;
    }

    public void setSTATUS_VAT(String STATUS_VAT) {
        this.STATUS_VAT = STATUS_VAT;
    }

    public double getNETTO() {
        return NETTO;
    }

    public void setNETTO(double NETTO) {
        this.NETTO = NETTO;
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    public double getNETTO_SYS() {
        return NETTO_SYS;
    }

    public void setNETTO_SYS(double NETTO_SYS) {
        this.NETTO_SYS = NETTO_SYS;
    }

    public double getVAT_SYS() {
        return VAT_SYS;
    }

    public void setVAT_SYS(double VAT_SYS) {
        this.VAT_SYS = VAT_SYS;
    }

    public double getNETTO_SYS2() {
        return NETTO_SYS2;
    }

    public void setNETTO_SYS2(double NETTO_SYS2) {
        this.NETTO_SYS2 = NETTO_SYS2;
    }

    public double getVAT_SYS2() {
        return VAT_SYS2;
    }

    public void setVAT_SYS2(double VAT_SYS2) {
        this.VAT_SYS2 = VAT_SYS2;
    }
}
