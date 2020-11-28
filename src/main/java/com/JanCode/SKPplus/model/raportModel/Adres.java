package com.JanCode.SKPplus.model.raportModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Adres {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String status;
    private String nazwa1;
    private String nazwa2;
    private String nazwa3;
    private String ulica;
    private String nr_domu;
    private String miasto;
    private String kod_pocztowy;
    private String poczta;
    private String nip_kraj;
    private String nip;

    public Adres() {
    }

    public Adres(long id, String status, String nazwa1, String nazwa2, String nazwa3, String ulica, String nr_domu, String miasto, String kod_pocztowy, String poczta, String nip_kraj, String nip) {
        this.id = id;
        this.status = status;
        this.nazwa1 = nazwa1;
        this.nazwa2 = nazwa2;
        this.nazwa3 = nazwa3;
        this.ulica = ulica;
        this.nr_domu = nr_domu;
        this.miasto = miasto;
        this.kod_pocztowy = kod_pocztowy;
        this.poczta = poczta;
        this.nip_kraj = nip_kraj;
        this.nip = nip;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNazwa1() {
        return nazwa1;
    }

    public void setNazwa1(String nazwa1) {
        this.nazwa1 = nazwa1;
    }

    public String getNazwa2() {
        return nazwa2;
    }

    public void setNazwa2(String nazwa2) {
        this.nazwa2 = nazwa2;
    }

    public String getNazwa3() {
        return nazwa3;
    }

    public void setNazwa3(String nazwa3) {
        this.nazwa3 = nazwa3;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNr_domu() {
        return nr_domu;
    }

    public void setNr_domu(String nr_domu) {
        this.nr_domu = nr_domu;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getKod_pocztowy() {
        return kod_pocztowy;
    }

    public void setKod_pocztowy(String kod_pocztowy) {
        this.kod_pocztowy = kod_pocztowy;
    }

    public String getPoczta() {
        return poczta;
    }

    public void setPoczta(String poczta) {
        this.poczta = poczta;
    }

    public String getNip_kraj() {
        return nip_kraj;
    }

    public void setNip_kraj(String nip_kraj) {
        this.nip_kraj = nip_kraj;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
}
