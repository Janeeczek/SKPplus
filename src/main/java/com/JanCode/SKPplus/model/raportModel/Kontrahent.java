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

    private int zrodloId;
    private int akronim;
    private String finalny;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "kontrahent_id",referencedColumnName = "id")
    private List<Adres> adresy;

    public Kontrahent() {
    }
    public Kontrahent(KontrahentDto kontrahentDto) {
        this.zrodloId = kontrahentDto.getID_ZRODLA();
        this.akronim = kontrahentDto.getAKRONIM();
        this.finalny = kontrahentDto.getFINALNY();
        List<Adres> adres = new ArrayList<>();
        List<AdresDto> adresDto = new ArrayList<>(kontrahentDto.getADRESY().getADRES());
        for (int i = 0; i < adresDto.size(); i++) {
            adres.add(new Adres(adresDto.get(i)));
        }


        this.adresy = adres;

    }

    public Kontrahent(long id, int zrodloId, int akronim, String finalny, List<Adres> adresy) {
        this.id = id;
        this.zrodloId = zrodloId;
        this.akronim = akronim;
        this.finalny = finalny;
        this.adresy = adresy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getZrodloId() {
        return zrodloId;
    }

    public void setZrodloId(int zrodloId) {
        this.zrodloId = zrodloId;
    }

    public int getAkronim() {
        return akronim;
    }

    public void setAkronim(int akronim) {
        this.akronim = akronim;
    }

    public String getFinalny() {
        return finalny;
    }

    public void setFinalny(String finalny) {
        this.finalny = finalny;
    }

    public List<Adres> getAdresy() {
        return adresy;
    }

    public void setAdresy(List<Adres> adresy) {
        this.adresy = adresy;
    }
}
