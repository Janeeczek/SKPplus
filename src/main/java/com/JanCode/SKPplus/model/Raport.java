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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kontrahenci_id")
    private Kontrahenci kontrahenci;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rejestrySprzedazyVat_id")
    private RejestrySprzedazyVat rejestrySprzedazyVat;


    public Raport() {
    }


    public Raport(DaneRaportuDto dane) {
        //TODO zrob tutaj cos
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
