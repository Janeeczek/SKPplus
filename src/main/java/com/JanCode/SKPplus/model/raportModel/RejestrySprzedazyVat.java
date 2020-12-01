package com.JanCode.SKPplus.model.raportModel;

import com.JanCode.SKPplus.model.Raport;
import com.JanCode.SKPplus.web.dto.kontrahenci.KontrahentDto;
import com.JanCode.SKPplus.web.dto.rejestrySprzedazy.RejestrSprzedazyVatDto;
import com.JanCode.SKPplus.web.dto.rejestrySprzedazy.RejestrySprzedazyVatDto;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "rejestr_sprzedazy_vat_id",referencedColumnName = "id")
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
    public RejestrySprzedazyVat(RejestrySprzedazyVatDto rejestrySprzedazyVatDto) {
        this.wersja = rejestrySprzedazyVatDto.getWERSJA();
        this.bazaZrdId = rejestrySprzedazyVatDto.getBAZA_ZRD_ID();
        this.bazaDocId = rejestrySprzedazyVatDto.getBAZA_DOC_ID();
        List<RejestrSprzedazyVatDto> rejDtoList =new ArrayList<>( rejestrySprzedazyVatDto.getREJESTR_SPRZEDAZY_VAT());
        List<RejestrSprzedazyVat> rejList = new ArrayList<>();
        for (int i = 0; i < rejDtoList.size(); i++) {
            rejList.add(new RejestrSprzedazyVat(rejDtoList.get(i)));
        }
        System.out.println("JEST "+ rejDtoList.size()+ " rejestrow sprzedazy dto");
        this.rejestrSprzedazyVat = rejList;
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
