package com.JanCode.SKPplus.model;

import com.JanCode.SKPplus.model.raportModel.Kontrahenci;
import com.JanCode.SKPplus.model.raportModel.Kontrahent;
import com.JanCode.SKPplus.model.raportModel.RejestrSprzedazyVat;
import com.JanCode.SKPplus.model.raportModel.RejestrySprzedazyVat;
import com.JanCode.SKPplus.web.dto.DaneRaportuDto;

import javax.persistence.*;
import java.time.LocalDate;


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

    private LocalDate dataUtworzenia;

    public Raport() {
    }


    public Raport(DaneRaportuDto daneRaportuDto) {
        this.kontrahenci =  new Kontrahenci(daneRaportuDto.getKONTRAHENCI());
        this.rejestrySprzedazyVat = new RejestrySprzedazyVat(daneRaportuDto.getREJESTRY_SPRZEDAZY_VAT());
        this.dataUtworzenia =  LocalDate.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Kontrahenci getKontrahenci() {
        return kontrahenci;
    }

    public void setKontrahenci(Kontrahenci kontrahenci) {
        this.kontrahenci = kontrahenci;
    }

    public RejestrySprzedazyVat getRejestrySprzedazyVat() {
        return rejestrySprzedazyVat;
    }

    public void setRejestrySprzedazyVat(RejestrySprzedazyVat rejestrySprzedazyVat) {
        this.rejestrySprzedazyVat = rejestrySprzedazyVat;
    }

    public LocalDate getDataUtworzenia() {
        return dataUtworzenia;
    }

    public void setDataUtworzenia(LocalDate dataUtworzenia) {
        this.dataUtworzenia = dataUtworzenia;
    }
}
