package com.JanCode.SKPplus.model.raportModel;

import com.JanCode.SKPplus.web.dto.rejestrySprzedazy.PozycjaDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Pozycja {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double stawka_vat;
    private String status_vat;
    private double netto;
    private double vat;
    private double netto_sys;
    private double vat_sys;
    private double netto_sys2;
    private double vat_sys2;

    public Pozycja() {
    }

    public Pozycja(long id, double stawka_vat, String status_vat, double netto, double vat, double netto_sys, double vat_sys, double netto_sys2, double vat_sys2) {
        this.id = id;
        this.stawka_vat = stawka_vat;
        this.status_vat = status_vat;
        this.netto = netto;
        this.vat = vat;
        this.netto_sys = netto_sys;
        this.vat_sys = vat_sys;
        this.netto_sys2 = netto_sys2;
        this.vat_sys2 = vat_sys2;
    }
    public Pozycja(PozycjaDto pozycjaDto) {
        this.stawka_vat = pozycjaDto.getSTAWKA_VAT();
        this.status_vat = pozycjaDto.getSTATUS_VAT();
        this.netto = pozycjaDto.getNETTO();
        this.vat = pozycjaDto.getVAT();
        this.netto_sys = pozycjaDto.getNETTO_SYS();
        this.vat_sys = pozycjaDto.getVAT_SYS();
        this.netto_sys2 = pozycjaDto.getNETTO_SYS2();
        this.vat_sys2 = pozycjaDto.getVAT_SYS2();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getStawka_vat() {
        return stawka_vat;
    }

    public void setStawka_vat(double stawka_vat) {
        this.stawka_vat = stawka_vat;
    }

    public String getStatus_vat() {
        return status_vat;
    }

    public void setStatus_vat(String status_vat) {
        this.status_vat = status_vat;
    }

    public double getNetto() {
        return netto;
    }

    public void setNetto(double netto) {
        this.netto = netto;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public double getNetto_sys() {
        return netto_sys;
    }

    public void setNetto_sys(double netto_sys) {
        this.netto_sys = netto_sys;
    }

    public double getVat_sys() {
        return vat_sys;
    }

    public void setVat_sys(double vat_sys) {
        this.vat_sys = vat_sys;
    }

    public double getNetto_sys2() {
        return netto_sys2;
    }

    public void setNetto_sys2(double netto_sys2) {
        this.netto_sys2 = netto_sys2;
    }

    public double getVat_sys2() {
        return vat_sys2;
    }

    public void setVat_sys2(double vat_sys2) {
        this.vat_sys2 = vat_sys2;
    }
}
