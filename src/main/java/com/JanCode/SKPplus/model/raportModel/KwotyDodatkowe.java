package com.JanCode.SKPplus.model.raportModel;

import com.JanCode.SKPplus.util.AdapterCDATA;
import com.JanCode.SKPplus.web.dto.rejestrySprzedazy.KwotyDodatkoweDto;
import com.JanCode.SKPplus.web.dto.rejestrySprzedazy.PozycjaKdDto;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;
import java.util.Set;

@Entity
public class KwotyDodatkowe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "pozycjaKd_id")
    private PozycjaKd pozycjaKd;
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String opisKd;

    public KwotyDodatkowe() {
    }

    public KwotyDodatkowe(long id, PozycjaKd pozycjaKd, String opisKd) {
        this.id = id;
        this.pozycjaKd = pozycjaKd;
        this.opisKd = opisKd;
    }
    public KwotyDodatkowe(KwotyDodatkoweDto kwotyDodatkoweDto) {
        if(kwotyDodatkoweDto == null)
        {
            this.pozycjaKd = new PozycjaKd();
            this.opisKd = null;
        }else{
            this.pozycjaKd = new PozycjaKd(kwotyDodatkoweDto.getPOZYCJA_KD());
            this.opisKd = kwotyDodatkoweDto.getOPIS_KD();
        }

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PozycjaKd getPozycjaKd() {
        return pozycjaKd;
    }

    public void setPozycjaKd(PozycjaKd pozycjaKd) {
        this.pozycjaKd = pozycjaKd;
    }

    public String getOpisKd() {
        return opisKd;
    }

    public void setOpisKd(String opisKd) {
        this.opisKd = opisKd;
    }
}
