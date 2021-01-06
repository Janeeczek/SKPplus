package com.JanCode.SKPplus.model.raportModel;

import com.JanCode.SKPplus.util.AdapterCDATA;
import com.JanCode.SKPplus.util.AdapterCDATADate;
import com.JanCode.SKPplus.web.dto.rejestrySprzedazy.PozycjaKdDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@Entity
public class PozycjaKd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String kategoria_kd = null;
    private Double kwota_kd = null;
    private Double kwota_kd_SYS = null;
    private String waluta_kd = null;
    private Double notowanie_waluty_ile_kd = null;
    private Double notowanie_waluty_za_ile_kd = null;
    private LocalDate data_kursu_kd = null;

    public PozycjaKd() {
    }

    public PozycjaKd(long id, String kategoria_kd, Double kwota_kd, Double kwota_kd_SYS, String waluta_kd, Double notowanie_waluty_ile_kd, Double notowanie_waluty_za_ile_kd, LocalDate  data_kursu_kd) {
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
        if (pozycjaKdDto != null)
        {
            this.kategoria_kd = pozycjaKdDto.getKATEGORIA_KD();
            this.kwota_kd = pozycjaKdDto.getKWOTA_KD();
            this.kwota_kd_SYS = pozycjaKdDto.getKWOTA_KD_SYS();
            this.waluta_kd = pozycjaKdDto.getWALUTA_KD();
            this.notowanie_waluty_ile_kd = pozycjaKdDto.getNOTOWANIE_WALUTY_ILE_KD();
            this.notowanie_waluty_za_ile_kd = pozycjaKdDto.getNOTOWANIE_WALUTY_ZA_ILE_KD();
            if ( pozycjaKdDto.getDATA_KURSU_KD() == null) {
                this.data_kursu_kd = null;
            }
            else {
                this.data_kursu_kd = LocalDate.parse(pozycjaKdDto.getDATA_KURSU_KD());
            }
        }

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

    public Double getKwota_kd() {
        return kwota_kd;
    }

    public void setKwota_kd(Double kwota_kd) {
        this.kwota_kd = kwota_kd;
    }

    public Double getKwota_kd_SYS() {
        return kwota_kd_SYS;
    }

    public void setKwota_kd_SYS(Double kwota_kd_SYS) {
        this.kwota_kd_SYS = kwota_kd_SYS;
    }

    public String getWaluta_kd() {
        return waluta_kd;
    }

    public void setWaluta_kd(String waluta_kd) {
        this.waluta_kd = waluta_kd;
    }

    public Double getNotowanie_waluty_ile_kd() {
        return notowanie_waluty_ile_kd;
    }

    public void setNotowanie_waluty_ile_kd(Double notowanie_waluty_ile_kd) {
        this.notowanie_waluty_ile_kd = notowanie_waluty_ile_kd;
    }

    public Double getNotowanie_waluty_za_ile_kd() {
        return notowanie_waluty_za_ile_kd;
    }

    public void setNotowanie_waluty_za_ile_kd(Double notowanie_waluty_za_ile_kd) {
        this.notowanie_waluty_za_ile_kd = notowanie_waluty_za_ile_kd;
    }

    public LocalDate getData_kursu_kd() {
        return data_kursu_kd;
    }

    public void setData_kursu_kd(LocalDate data_kursu_kd) {
        this.data_kursu_kd = data_kursu_kd;
    }
}
