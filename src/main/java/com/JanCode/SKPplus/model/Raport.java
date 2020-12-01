package com.JanCode.SKPplus.model;

import com.JanCode.SKPplus.model.raportModel.Kontrahenci;
import com.JanCode.SKPplus.model.raportModel.Kontrahent;
import com.JanCode.SKPplus.model.raportModel.RejestrSprzedazyVat;
import com.JanCode.SKPplus.model.raportModel.RejestrySprzedazyVat;
import com.JanCode.SKPplus.web.dto.DaneRaportuDto;

import javax.persistence.*;


@Entity
public class Raport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "kontrahenci_id")
    private Kontrahenci kontrahenci;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "rejestrySprzedazyVat_id")
    private RejestrySprzedazyVat rejestrySprzedazyVat;


    public Raport() {
    }


    public Raport(DaneRaportuDto daneRaportuDto) {
        this.kontrahenci =  new Kontrahenci(daneRaportuDto.getKONTRAHENCI());
        //this.rejestrySprzedazyVat = daneRaportuDto.getREJESTRY_SPRZEDAZY_VAT();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
