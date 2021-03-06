package com.JanCode.SKPplus.model;

import com.JanCode.SKPplus.model.raportModel.Kontrahenci;
import com.JanCode.SKPplus.model.raportModel.Kontrahent;
import com.JanCode.SKPplus.model.raportModel.RejestrSprzedazyVat;
import com.JanCode.SKPplus.model.raportModel.RejestrySprzedazyVat;
import com.JanCode.SKPplus.web.dto.DaneRaportuDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name="RAPORT")
public class Raport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nazwa;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "kontrahenci_id")
    private Kontrahenci kontrahenci;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "rejestrySprzedazyVat_id")
    private RejestrySprzedazyVat rejestrySprzedazyVat;

    private LocalDateTime dataUtworzenia;
    @ManyToOne
    @JoinColumn(name="source_file_id", nullable=false)
    private FileDB files;
    @ManyToOne
    @JoinColumn(name="creator_id", nullable=false)
    private User user;

    public Raport() {
    }


    public Raport(String nazwa, DaneRaportuDto daneRaportuDto, User user, FileDB files) {
        //this.xmlns = daneRaportuDto.getXmlns();
        this.kontrahenci =  new Kontrahenci(daneRaportuDto.getKONTRAHENCI());
        this.rejestrySprzedazyVat = new RejestrySprzedazyVat(daneRaportuDto.getREJESTRY_SPRZEDAZY_VAT());
        this.dataUtworzenia =  LocalDateTime.now();
        this.user = user;
        this.files = files;
        this.nazwa = nazwa;
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

    public LocalDateTime getDataUtworzenia() {
        return dataUtworzenia;
    }

    public void setDataUtworzenia(LocalDateTime dataUtworzenia) {
        this.dataUtworzenia = dataUtworzenia;
    }

    public FileDB getFileDB() {
        return files;
    }

    public void setFileDB(FileDB files) {
        this.files = files;
    }

    public FileDB getFiles() {
        return files;
    }

    public void setFiles(FileDB files) {
        this.files = files;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
