package com.JanCode.SKPplus.model.raportModel;

import com.JanCode.SKPplus.web.dto.rejestrySprzedazy.PozycjaDto;
import com.JanCode.SKPplus.web.dto.rejestrySprzedazy.PozycjeDto;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Proxy(lazy = false)
public class Pozycje {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "pozycje_id",referencedColumnName = "id")
    private List<Pozycja> pozycja;

    public Pozycje() {
    }

    public Pozycje(List<Pozycja> pozycja) {
        this.pozycja = pozycja;
    }
    public Pozycje(PozycjeDto pozycjeDto) {
        List<Pozycja> pozycjaList = new ArrayList<>();
        List<PozycjaDto> pozycjaDtoList = new ArrayList<>(pozycjeDto.getPOZYCJA());
        for (int i = 0; i< pozycjaDtoList.size();i++) {
            pozycjaList.add(new Pozycja(pozycjaDtoList.get(i)));
        }
        this.pozycja = pozycjaList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Pozycja> getPozycja() {
        return pozycja;
    }

    public void setPozycja(List<Pozycja> pozycja) {
        this.pozycja = pozycja;
    }
}
