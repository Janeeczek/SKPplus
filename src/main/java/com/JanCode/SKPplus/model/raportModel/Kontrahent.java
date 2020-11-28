package com.JanCode.SKPplus.model.raportModel;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Kontrahent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int zrodloId;
    private int akronim;
    private String finalny;

    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name = "kontrahent_id",referencedColumnName = "id")
    private List<Adres> adresy = new ArrayList<>();

    public Kontrahent() {
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
