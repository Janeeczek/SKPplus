package com.JanCode.SKPplus.model.raportModel;

import com.JanCode.SKPplus.model.Raport;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class RejestrySprzedazyVat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double wersja;
    private String bazaZrdId;
    private String bazaDocId;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "rejestrySprzedazyVat_rejestrSprzedazyVat",
            joinColumns = @JoinColumn(
                    name = "rejestrySprzedazyVat_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "rejestrSprzedazyVat_id", referencedColumnName = "id"))
    private List<RejestrSprzedazyVat> rejestrSprzedazyVat;

    public RejestrySprzedazyVat() {
    }

    public RejestrySprzedazyVat(long id, double wersja, String bazaZrdId, String bazaDocId, List<RejestrSprzedazyVat> rejestrSprzedazyVat) {
        this.id = id;
        this.wersja = wersja;
        this.bazaZrdId = bazaZrdId;
        this.bazaDocId = bazaDocId;
        this.rejestrSprzedazyVat = rejestrSprzedazyVat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getWersja() {
        return wersja;
    }

    public void setWersja(double wersja) {
        this.wersja = wersja;
    }

    public String getBazaZrdId() {
        return bazaZrdId;
    }

    public void setBazaZrdId(String bazaZrdId) {
        this.bazaZrdId = bazaZrdId;
    }

    public String getBazaDocId() {
        return bazaDocId;
    }

    public void setBazaDocId(String bazaDocId) {
        this.bazaDocId = bazaDocId;
    }

    public List<RejestrSprzedazyVat> getRejestrSprzedazyVat() {
        return rejestrSprzedazyVat;
    }

    public void setRejestrSprzedazyVat(List<RejestrSprzedazyVat> rejestrSprzedazyVat) {
        this.rejestrSprzedazyVat = rejestrSprzedazyVat;
    }
}
