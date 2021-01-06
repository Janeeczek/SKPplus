package com.JanCode.SKPplus.model.raportModel;
import com.JanCode.SKPplus.web.dto.kontrahenci.AdresDto;
import com.JanCode.SKPplus.web.dto.kontrahenci.AdresyDto;
import com.JanCode.SKPplus.web.dto.kontrahenci.KontrahentDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Entity
public class Kontrahent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String zrodloId;
    private String akronim;
    private String finalny;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "adresy_id")
    private Adresy adresy;

    public Kontrahent() { }
    public Kontrahent(long id, String zrodloId, String akronim, String finalny,Adresy adresy) {
        this.id = id;
        this.zrodloId = zrodloId;
        this.akronim = akronim;
        this.finalny = finalny;
        this.adresy = adresy;
    }

    public Kontrahent(KontrahentDto kontrahentDto) {
        this.zrodloId = kontrahentDto.getID_ZRODLA();
        this.akronim = kontrahentDto.getAKRONIM();
        this.finalny = kontrahentDto.getFINALNY();
        this.adresy = new Adresy(kontrahentDto.getADRESY());

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getZrodloId() {
        return zrodloId;
    }

    public void setZrodloId(String zrodloId) {
        this.zrodloId = zrodloId;
    }

    public String getAkronim() {
        return akronim;
    }

    public void setAkronim(String akronim) {
        this.akronim = akronim;
    }

    public String getFinalny() {
        return finalny;
    }

    public void setFinalny(String finalny) {
        this.finalny = finalny;
    }

    public Adresy getAdresy() {
        return adresy;
    }

    public void setAdresy(Adresy adresy) {
        this.adresy = adresy;
    }
}
