package com.JanCode.SKPplus.model.raportModel;

import com.JanCode.SKPplus.model.Raport;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
@Entity
public class Kontrahenci {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double wersja;
    private String bazaZrdId;
    private String bazaDocId;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    @JoinTable(
            name = "kontrahenci_kontrahent",
            joinColumns = @JoinColumn(
                    name = "kontrahenci_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "kontrahent_id", referencedColumnName = "id"))
    private List<Kontrahent> kontrahent;

    public Kontrahenci() {
    }

    public Kontrahenci(long id, double wersja, String bazaZrdId, String bazaDocId, List<Kontrahent> kontrahent) {
        this.id = id;
        this.wersja = wersja;
        this.bazaZrdId = bazaZrdId;
        this.bazaDocId = bazaDocId;
        this.kontrahent = kontrahent;
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

    public List<Kontrahent> getKontrahent() {
        return kontrahent;
    }

    public void setKontrahent(List<Kontrahent> kontrahent) {
        this.kontrahent = kontrahent;
    }
}
