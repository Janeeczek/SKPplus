package com.JanCode.SKPplus.model.raportModel;

import com.JanCode.SKPplus.web.dto.rejestrySprzedazy.PlatnoscDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.crypto.Data;
import java.time.LocalDate;

@Entity
public class Platnosc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate termin_plat;
    private String forma_platnosci_plat;
    private double kwota_plat;
    private String waluta_plat;
    private String kurs_waluty_plat;
    private double notowanie_waluty_ile_plat;
    private double notowanie_waluty_za_ile_plat;
    private double kwota_pln_plat;
    private String kierunek;
    private String podlega_rozliczeniu;
    private String konto;
    private LocalDate data_kursu_plat;
    private String waluta_dok;

    public Platnosc() {
    }

    public Platnosc(long id, LocalDate  termin_plat, String forma_platnosci_plat, double kwota_plat, String waluta_plat, String kurs_waluty_plat, double notowanie_waluty_ile_plat, double notowanie_waluty_za_ile_plat, double kwota_pln_plat, String kierunek, String podlega_rozliczeniu, String konto, LocalDate data_kursu_plat, String waluta_dok) {
        this.id = id;
        this.termin_plat = termin_plat;
        this.forma_platnosci_plat = forma_platnosci_plat;
        this.kwota_plat = kwota_plat;
        this.waluta_plat = waluta_plat;
        this.kurs_waluty_plat = kurs_waluty_plat;
        this.notowanie_waluty_ile_plat = notowanie_waluty_ile_plat;
        this.notowanie_waluty_za_ile_plat = notowanie_waluty_za_ile_plat;
        this.kwota_pln_plat = kwota_pln_plat;
        this.kierunek = kierunek;
        this.podlega_rozliczeniu = podlega_rozliczeniu;
        this.konto = konto;
        this.data_kursu_plat = data_kursu_plat;
        this.waluta_dok = waluta_dok;
    }
    public Platnosc(PlatnoscDto platnoscDto) {
        this.termin_plat = LocalDate.parse(platnoscDto.getTERMIN_PLAT());
        this.forma_platnosci_plat = platnoscDto.getFORMA_PLATNOSCI_PLAT();
        this.kwota_plat = platnoscDto.getKWOTA_PLAT();
        this.waluta_plat = platnoscDto.getWALUTA_PLAT();
        this.kurs_waluty_plat = platnoscDto.getKURS_WALUTY_PLAT();
        this.notowanie_waluty_ile_plat = platnoscDto.getNOTOWANIE_WALUTY_ILE_PLAT();
        this.notowanie_waluty_za_ile_plat = platnoscDto.getNOTOWANIE_WALUTY_ZA_ILE_PLAT();
        this.kwota_pln_plat = platnoscDto.getKWOTA_PLN_PLAT();
        this.kierunek = platnoscDto.getKIERUNEK();
        this.podlega_rozliczeniu = platnoscDto.getPODLEGA_ROZLICZENIU();
        this.konto = platnoscDto.getKONTO();
        this.data_kursu_plat = LocalDate.parse(platnoscDto.getDATA_KURSU_PLAT());
        this.waluta_dok = platnoscDto.getWALUTA_DOK();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate  getTermin_plat() {
        return termin_plat;
    }

    public void setTermin_plat(LocalDate termin_plat) {
        this.termin_plat = termin_plat;
    }

    public String getForma_platnosci_plat() {
        return forma_platnosci_plat;
    }

    public void setForma_platnosci_plat(String forma_platnosci_plat) {
        this.forma_platnosci_plat = forma_platnosci_plat;
    }

    public double getKwota_plat() {
        return kwota_plat;
    }

    public void setKwota_plat(double kwota_plat) {
        this.kwota_plat = kwota_plat;
    }

    public String getWaluta_plat() {
        return waluta_plat;
    }

    public void setWaluta_plat(String waluta_plat) {
        this.waluta_plat = waluta_plat;
    }

    public String getKurs_waluty_plat() {
        return kurs_waluty_plat;
    }

    public void setKurs_waluty_plat(String kurs_waluty_plat) {
        this.kurs_waluty_plat = kurs_waluty_plat;
    }

    public double getNotowanie_waluty_ile_plat() {
        return notowanie_waluty_ile_plat;
    }

    public void setNotowanie_waluty_ile_plat(double notowanie_waluty_ile_plat) {
        this.notowanie_waluty_ile_plat = notowanie_waluty_ile_plat;
    }

    public double getNotowanie_waluty_za_ile_plat() {
        return notowanie_waluty_za_ile_plat;
    }

    public void setNotowanie_waluty_za_ile_plat(double notowanie_waluty_za_ile_plat) {
        this.notowanie_waluty_za_ile_plat = notowanie_waluty_za_ile_plat;
    }

    public double getKwota_pln_plat() {
        return kwota_pln_plat;
    }

    public void setKwota_pln_plat(double kwota_pln_plat) {
        this.kwota_pln_plat = kwota_pln_plat;
    }

    public String getKierunek() {
        return kierunek;
    }

    public void setKierunek(String kierunek) {
        this.kierunek = kierunek;
    }

    public String getPodlega_rozliczeniu() {
        return podlega_rozliczeniu;
    }

    public void setPodlega_rozliczeniu(String podlega_rozliczeniu) {
        this.podlega_rozliczeniu = podlega_rozliczeniu;
    }

    public String getKonto() {
        return konto;
    }

    public void setKonto(String konto) {
        this.konto = konto;
    }

    public LocalDate  getData_kursu_plat() {
        return data_kursu_plat;
    }

    public void setData_kursu_plat(LocalDate  data_kursu_plat) {
        this.data_kursu_plat = data_kursu_plat;
    }

    public String getWaluta_dok() {
        return waluta_dok;
    }

    public void setWaluta_dok(String waluta_dok) {
        this.waluta_dok = waluta_dok;
    }
}
