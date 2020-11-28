package com.JanCode.SKPplus.model.raportModel;

import com.JanCode.SKPplus.web.dto.rejestrySprzedazy.PozycjaKdDto;

import javax.persistence.*;
import java.util.List;

@Entity
public class KwotyDodatkowe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name = "kwoty_dodatkowe_id",referencedColumnName = "id")
    private List<PozycjaKd> pozycjakd;

    private String opiskd;

    public KwotyDodatkowe() {
    }

    public KwotyDodatkowe(long id, List<PozycjaKd> pozycjakd, String opiskd) {
        this.id = id;
        this.pozycjakd = pozycjakd;
        this.opiskd = opiskd;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<PozycjaKd> getPozycjakd() {
        return pozycjakd;
    }

    public void setPozycjakd(List<PozycjaKd> pozycjakd) {
        this.pozycjakd = pozycjakd;
    }

    public String getOpiskd() {
        return opiskd;
    }

    public void setOpiskd(String opiskd) {
        this.opiskd = opiskd;
    }
}
