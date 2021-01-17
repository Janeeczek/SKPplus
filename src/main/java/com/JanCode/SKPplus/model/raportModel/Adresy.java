package com.JanCode.SKPplus.model.raportModel;

import com.JanCode.SKPplus.web.dto.kontrahenci.AdresDto;
import com.JanCode.SKPplus.web.dto.kontrahenci.AdresyDto;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Proxy(lazy = false)
public class Adresy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "adres_id",referencedColumnName = "id")
    private List<Adres> adres;

    public Adresy() {
    }

    public Adresy(AdresyDto adresyDto) {
        List<Adres> adresList = new ArrayList<>();
        List<AdresDto> adresDtoList = new ArrayList<>(adresyDto.getADRES());
        for (int i = 0; i < adresDtoList.size(); i++) {
            adresList.add(new Adres(adresDtoList.get(i)));
        }
        this.adres = adresList;
    }

    public Adresy(List<Adres> adres) {
        this.adres = adres;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Adres> getAdres() {
        return adres;
    }

    public void setAdres(List<Adres> adres) {
        this.adres = adres;
    }
}
