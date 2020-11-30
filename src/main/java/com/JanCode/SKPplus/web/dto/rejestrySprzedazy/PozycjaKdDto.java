package com.JanCode.SKPplus.web.dto.rejestrySprzedazy;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "POZYCJA_KD")
@XmlType(propOrder={"KATEGORIA_KD","KWOTA_KD","KWOTA_KD_SYS","WALUTA_KD","NOTOWANIE_WALUTY_ILE_KD","NOTOWANIE_WALUTY_ZA_ILE_KD","DATA_KURSU_KD"})
public class PozycjaKdDto {
    private String KATEGORIA_KD;
    private double KWOTA_KD;
    private double KWOTA_KD_SYS;
    private String WALUTA_KD;
    private double NOTOWANIE_WALUTY_ILE_KD;
    private double NOTOWANIE_WALUTY_ZA_ILE_KD;
    private String DATA_KURSU_KD;
    public PozycjaKdDto() {
    }

    public PozycjaKdDto(String KATEGORIA_KD, double KWOTA_KD, double KWOTA_KD_SYS, String WALUTA_KD, double NOTOWANIE_WALUTY_ILE_KD, double NOTOWANIE_WALUTY_ZA_ILE_KD, String DATA_KURSU_KD) {
        this.KATEGORIA_KD = KATEGORIA_KD;
        this.KWOTA_KD = KWOTA_KD;
        this.KWOTA_KD_SYS = KWOTA_KD_SYS;
        this.WALUTA_KD = WALUTA_KD;
        this.NOTOWANIE_WALUTY_ILE_KD = NOTOWANIE_WALUTY_ILE_KD;
        this.NOTOWANIE_WALUTY_ZA_ILE_KD = NOTOWANIE_WALUTY_ZA_ILE_KD;
        this.DATA_KURSU_KD = DATA_KURSU_KD;
    }

    public String getKATEGORIA_KD() {
        return KATEGORIA_KD;
    }

    public void setKATEGORIA_KD(String KATEGORIA_KD) {
        this.KATEGORIA_KD = KATEGORIA_KD;
    }

    public double getKWOTA_KD() {
        return KWOTA_KD;
    }

    public void setKWOTA_KD(double KWOTA_KD) {
        this.KWOTA_KD = KWOTA_KD;
    }

    public double getKWOTA_KD_SYS() {
        return KWOTA_KD_SYS;
    }

    public void setKWOTA_KD_SYS(double KWOTA_KD_SYS) {
        this.KWOTA_KD_SYS = KWOTA_KD_SYS;
    }

    public String getWALUTA_KD() {
        return WALUTA_KD;
    }

    public void setWALUTA_KD(String WALUTA_KD) {
        this.WALUTA_KD = WALUTA_KD;
    }

    public double getNOTOWANIE_WALUTY_ILE_KD() {
        return NOTOWANIE_WALUTY_ILE_KD;
    }

    public void setNOTOWANIE_WALUTY_ILE_KD(double NOTOWANIE_WALUTY_ILE_KD) {
        this.NOTOWANIE_WALUTY_ILE_KD = NOTOWANIE_WALUTY_ILE_KD;
    }

    public double getNOTOWANIE_WALUTY_ZA_ILE_KD() {
        return NOTOWANIE_WALUTY_ZA_ILE_KD;
    }

    public void setNOTOWANIE_WALUTY_ZA_ILE_KD(double NOTOWANIE_WALUTY_ZA_ILE_KD) {
        this.NOTOWANIE_WALUTY_ZA_ILE_KD = NOTOWANIE_WALUTY_ZA_ILE_KD;
    }

    public String getDATA_KURSU_KD() {
        return DATA_KURSU_KD;
    }

    public void setDATA_KURSU_KD(String DATA_KURSU_KD) {
        this.DATA_KURSU_KD = DATA_KURSU_KD;
    }
}
