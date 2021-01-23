package com.JanCode.SKPplus.model.raportModel;

import com.JanCode.SKPplus.web.dto.rejestrySprzedazy.PlatnoscDto;
import com.JanCode.SKPplus.web.dto.rejestrySprzedazy.PlatnosciDto;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Proxy(lazy = false)
public class Platnosci {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "platnosci_id",referencedColumnName = "id")
    private List<Platnosc> platnosc;

    public Platnosci() {
    }

    public Platnosci(List<Platnosc> platnosc) {
        this.platnosc = platnosc;
    }

    public Platnosci(PlatnosciDto platnosciDto) {
        List<Platnosc> platnoscList = new ArrayList<>();
        List<PlatnoscDto> platnoscDtoList = new ArrayList<>(platnosciDto.getPLATNOSC());
        for(int i = 0; i< platnoscDtoList.size();i++) {
            platnoscList.add(new Platnosc(platnoscDtoList.get(i)));
        }
        this.platnosc = platnoscList;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Platnosc> getPlatnosc() {
        return platnosc;
    }

    public void setPlatnosc(List<Platnosc> platnosc) {
        this.platnosc = platnosc;
    }
}
