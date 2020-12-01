package com.JanCode.SKPplus.model.raportModel;

import com.JanCode.SKPplus.web.dto.rejestrySprzedazy.PozycjaKdDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class PozycjaKd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String kategoria_kd;
    private double kwota_kd;
    private double kwota_kd_SYS;
    private String waluta_kd;
    private double notowanie_waluty_ile_kd;
    private double notowanie_waluty_za_ile_kd;
    private LocalDate data_kursu_kd;

    public PozycjaKd() {
    }

    public PozycjaKd(long id, String kategoria_kd, double kwota_kd, double kwota_kd_SYS, String waluta_kd, double notowanie_waluty_ile_kd, double notowanie_waluty_za_ile_kd, LocalDate  data_kursu_kd) {
        this.id = id;
        this.kategoria_kd = kategoria_kd;
        this.kwota_kd = kwota_kd;
        this.kwota_kd_SYS = kwota_kd_SYS;
        this.waluta_kd = waluta_kd;
        this.notowanie_waluty_ile_kd = notowanie_waluty_ile_kd;
        this.notowanie_waluty_za_ile_kd = notowanie_waluty_za_ile_kd;
        this.data_kursu_kd = data_kursu_kd;
    }
    public PozycjaKd(PozycjaKdDto pozycjaKdDto) {
        this.kategoria_kd = pozycjaKdDto.getKATEGORIA_KD();
        this.kwota_kd = pozycjaKdDto.getKWOTA_KD();
        this.kwota_kd_SYS = pozycjaKdDto.getKWOTA_KD_SYS();
        this.waluta_kd = pozycjaKdDto.getWALUTA_KD();
        this.notowanie_waluty_ile_kd = pozycjaKdDto.getNOTOWANIE_WALUTY_ILE_KD();
        this.notowanie_waluty_za_ile_kd = pozycjaKdDto.getNOTOWANIE_WALUTY_ZA_ILE_KD();
        this.data_kursu_kd = LocalDate.parse(pozycjaKdDto.getDATA_KURSU_KD());
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKategoria_kd() {
        return kategoria_kd;
    }

    public void setKategoria_kd(String kategoria_kd) {
        this.kategoria_kd = kategoria_kd;
    }

    public double getKwota_kd() {
        return kwota_kd;
    }

    public void setKwota_kd(double kwota_kd) {
        this.kwota_kd = kwota_kd;
    }

    public double getKwota_kd_SYS() {
        return kwota_kd_SYS;
    }

    public void setKwota_kd_SYS(double kwota_kd_SYS) {
        this.kwota_kd_SYS = kwota_kd_SYS;
    }

    public String getWaluta_kd() {
        return waluta_kd;
    }

    public void setWaluta_kd(String waluta_kd) {
        this.waluta_kd = waluta_kd;
    }

    public double getNotowanie_waluty_ile_kd() {
        return notowanie_waluty_ile_kd;
    }

    public void setNotowanie_waluty_ile_kd(double notowanie_waluty_ile_kd) {
        this.notowanie_waluty_ile_kd = notowanie_waluty_ile_kd;
    }

    public double getNotowanie_waluty_za_ile_kd() {
        return notowanie_waluty_za_ile_kd;
    }

    public void setNotowanie_waluty_za_ile_kd(double notowanie_waluty_za_ile_kd) {
        this.notowanie_waluty_za_ile_kd = notowanie_waluty_za_ile_kd;
    }

    public LocalDate getData_kursu_kd() {
        return data_kursu_kd;
    }

    public void setData_kursu_kd(LocalDate data_kursu_kd) {
        this.data_kursu_kd = data_kursu_kd;
    }
}
