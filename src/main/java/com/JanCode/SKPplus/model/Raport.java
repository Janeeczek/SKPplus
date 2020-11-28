package com.JanCode.SKPplus.model;

import com.JanCode.SKPplus.model.raportModel.Kontrahent;
import com.JanCode.SKPplus.model.raportModel.RejestrSprzedazyVat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Raport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "raporty_kontrahenci",
            joinColumns = @JoinColumn(
                    name = "raport_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "kontrahent_id", referencedColumnName = "id"))
    private List<Kontrahent> kontrahenci = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "raporty_rejestrSprzedazyVat",
            joinColumns = @JoinColumn(
                    name = "raport_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "rejestrSprzedazyVat_id", referencedColumnName = "id"))
    private List<RejestrSprzedazyVat> rejestrySprzedazyVat = new ArrayList<>();

    public Raport() {
    }

    public Raport(long id, List<Kontrahent> kontrahenci, List<RejestrSprzedazyVat> rejestrySprzedazyVat) {
        this.id = id;
        this.kontrahenci = kontrahenci;
        this.rejestrySprzedazyVat = rejestrySprzedazyVat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Kontrahent> getKontrahenci() {
        return kontrahenci;
    }

    public void setKontrahenci(List<Kontrahent> kontrahenci) {
        this.kontrahenci = kontrahenci;
    }

    public List<RejestrSprzedazyVat> getRejestrySprzedazyVat() {
        return rejestrySprzedazyVat;
    }

    public void setRejestrySprzedazyVat(List<RejestrSprzedazyVat> rejestrySprzedazyVat) {
        this.rejestrySprzedazyVat = rejestrySprzedazyVat;
    }
}
