package com.JanCode.SKPplus.web.dto.rejestrySprzedazy;

import com.JanCode.SKPplus.model.raportModel.PozycjaKd;
import com.JanCode.SKPplus.util.AdapterCDATA;
import com.JanCode.SKPplus.util.AdapterCDATADate;
import com.JanCode.SKPplus.util.AdapterDouble;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name = "POZYCJA_KD")
@XmlType(propOrder={"KATEGORIA_KD","KWOTA_KD","KWOTA_KD_SYS","WALUTA_KD","NOTOWANIE_WALUTY_ILE_KD","NOTOWANIE_WALUTY_ZA_ILE_KD","DATA_KURSU_KD"})
public class PozycjaKdDto {
    private String KATEGORIA_KD = null;
    private Double KWOTA_KD = null;
    private Double KWOTA_KD_SYS = null;
    private String WALUTA_KD = null;
    private Double NOTOWANIE_WALUTY_ILE_KD = null;
    private Double NOTOWANIE_WALUTY_ZA_ILE_KD = null;
    private String DATA_KURSU_KD = null;
    public PozycjaKdDto() {
    }

    public PozycjaKdDto(String KATEGORIA_KD, Double KWOTA_KD, Double KWOTA_KD_SYS, String WALUTA_KD, Double NOTOWANIE_WALUTY_ILE_KD, Double NOTOWANIE_WALUTY_ZA_ILE_KD, String DATA_KURSU_KD) {
        this.KATEGORIA_KD = KATEGORIA_KD;
        this.KWOTA_KD = KWOTA_KD;
        this.KWOTA_KD_SYS = KWOTA_KD_SYS;
        this.WALUTA_KD = WALUTA_KD;
        this.NOTOWANIE_WALUTY_ILE_KD = NOTOWANIE_WALUTY_ILE_KD;
        this.NOTOWANIE_WALUTY_ZA_ILE_KD = NOTOWANIE_WALUTY_ZA_ILE_KD;
        this.DATA_KURSU_KD = DATA_KURSU_KD;
    }

    public PozycjaKdDto(PozycjaKd pozycjaKd) {
        if (pozycjaKd != null) {
            this.KATEGORIA_KD = pozycjaKd.getKategoria_kd();
            this.KWOTA_KD = pozycjaKd.getKwota_kd();
            this.KWOTA_KD_SYS = pozycjaKd.getKwota_kd_SYS();
            this.WALUTA_KD = pozycjaKd.getWaluta_kd();
            this.NOTOWANIE_WALUTY_ILE_KD = pozycjaKd.getNotowanie_waluty_ile_kd();
            this.NOTOWANIE_WALUTY_ZA_ILE_KD = pozycjaKd.getNotowanie_waluty_za_ile_kd();
            this.DATA_KURSU_KD = pozycjaKd.getData_kursu_kd().toString();
        } else {
            this.KATEGORIA_KD = null;
            this.KWOTA_KD = null;
            this.KWOTA_KD_SYS = null;
            this.WALUTA_KD = null;
            this.NOTOWANIE_WALUTY_ILE_KD = null;
            this.NOTOWANIE_WALUTY_ZA_ILE_KD = null;
            this.DATA_KURSU_KD = null;
        }

    }
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    public String getKATEGORIA_KD() {
        return KATEGORIA_KD;
    }

    public void setKATEGORIA_KD(String KATEGORIA_KD) {
        this.KATEGORIA_KD = KATEGORIA_KD;
    }
    @XmlJavaTypeAdapter(AdapterDouble.class)
    public Double getKWOTA_KD() {
        return KWOTA_KD;
    }

    public void setKWOTA_KD(Double KWOTA_KD) {
        this.KWOTA_KD = KWOTA_KD;
    }
    @XmlJavaTypeAdapter(AdapterDouble.class)
    public Double getKWOTA_KD_SYS() {
        return KWOTA_KD_SYS;
    }

    public void setKWOTA_KD_SYS(Double KWOTA_KD_SYS) {
        this.KWOTA_KD_SYS = KWOTA_KD_SYS;
    }
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    public String getWALUTA_KD() {
        return WALUTA_KD;
    }

    public void setWALUTA_KD(String WALUTA_KD) {
        this.WALUTA_KD = WALUTA_KD;
    }
    @XmlJavaTypeAdapter(AdapterDouble.class)
    public Double getNOTOWANIE_WALUTY_ILE_KD() {
        return NOTOWANIE_WALUTY_ILE_KD;
    }

    public void setNOTOWANIE_WALUTY_ILE_KD(Double NOTOWANIE_WALUTY_ILE_KD) {
        this.NOTOWANIE_WALUTY_ILE_KD = NOTOWANIE_WALUTY_ILE_KD;
    }
    @XmlJavaTypeAdapter(AdapterDouble.class)
    public Double getNOTOWANIE_WALUTY_ZA_ILE_KD() {
        return NOTOWANIE_WALUTY_ZA_ILE_KD;
    }

    public void setNOTOWANIE_WALUTY_ZA_ILE_KD(Double NOTOWANIE_WALUTY_ZA_ILE_KD) {
        this.NOTOWANIE_WALUTY_ZA_ILE_KD = NOTOWANIE_WALUTY_ZA_ILE_KD;
    }
    public String getDATA_KURSU_KD() {
        return DATA_KURSU_KD;
    }

    public void setDATA_KURSU_KD(String DATA_KURSU_KD) {
        this.DATA_KURSU_KD = DATA_KURSU_KD;
    }
}
