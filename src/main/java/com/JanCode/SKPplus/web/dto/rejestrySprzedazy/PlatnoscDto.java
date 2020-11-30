package com.JanCode.SKPplus.web.dto.rejestrySprzedazy;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "PLATNOSC")
@XmlType(propOrder={"TERMIN_PLAT","FORMA_PLATNOSCI_PLAT","KWOTA_PLAT","WALUTA_PLAT","KURS_WALUTY_PLAT","NOTOWANIE_WALUTY_ILE_PLAT","NOTOWANIE_WALUTY_ZA_ILE_PLAT",
        "KWOTA_PLN_PLAT","KIERUNEK","PODLEGA_ROZLICZENIU","KONTO","DATA_KURSU_PLAT","WALUTA_DOK"})
public class PlatnoscDto {
    private String TERMIN_PLAT;
    private String FORMA_PLATNOSCI_PLAT;
    private double KWOTA_PLAT;
    private String WALUTA_PLAT;
    private String KURS_WALUTY_PLAT;
    private double NOTOWANIE_WALUTY_ILE_PLAT;
    private double NOTOWANIE_WALUTY_ZA_ILE_PLAT;
    private double KWOTA_PLN_PLAT;
    private String KIERUNEK;
    private String PODLEGA_ROZLICZENIU;
    private String KONTO;
    private String DATA_KURSU_PLAT;
    private String WALUTA_DOK;

    public PlatnoscDto() {
    }

    public PlatnoscDto(String TERMIN_PLAT, String FORMA_PLATNOSCI_PLAT, double KWOTA_PLAT, String WALUTA_PLAT, String KURS_WALUTY_PLAT, double NOTOWANIE_WALUTY_ILE_PLAT, double NOTOWANIE_WALUTY_ZA_ILE_PLAT, double KWOTA_PLN_PLAT, String KIERUNEK, String PODLEGA_ROZLICZENIU, String KONTO, String DATA_KURSU_PLAT, String WALUTA_DOK) {
        this.TERMIN_PLAT = TERMIN_PLAT;
        this.FORMA_PLATNOSCI_PLAT = FORMA_PLATNOSCI_PLAT;
        this.KWOTA_PLAT = KWOTA_PLAT;
        this.WALUTA_PLAT = WALUTA_PLAT;
        this.KURS_WALUTY_PLAT = KURS_WALUTY_PLAT;
        this.NOTOWANIE_WALUTY_ILE_PLAT = NOTOWANIE_WALUTY_ILE_PLAT;
        this.NOTOWANIE_WALUTY_ZA_ILE_PLAT = NOTOWANIE_WALUTY_ZA_ILE_PLAT;
        this.KWOTA_PLN_PLAT = KWOTA_PLN_PLAT;
        this.KIERUNEK = KIERUNEK;
        this.PODLEGA_ROZLICZENIU = PODLEGA_ROZLICZENIU;
        this.KONTO = KONTO;
        this.DATA_KURSU_PLAT = DATA_KURSU_PLAT;
        this.WALUTA_DOK = WALUTA_DOK;
    }

    public String getTERMIN_PLAT() {
        return TERMIN_PLAT;
    }

    public void setTERMIN_PLAT(String TERMIN_PLAT) {
        this.TERMIN_PLAT = TERMIN_PLAT;
    }

    public String getFORMA_PLATNOSCI_PLAT() {
        return FORMA_PLATNOSCI_PLAT;
    }

    public void setFORMA_PLATNOSCI_PLAT(String FORMA_PLATNOSCI_PLAT) {
        this.FORMA_PLATNOSCI_PLAT = FORMA_PLATNOSCI_PLAT;
    }

    public double getKWOTA_PLAT() {
        return KWOTA_PLAT;
    }

    public void setKWOTA_PLAT(double KWOTA_PLAT) {
        this.KWOTA_PLAT = KWOTA_PLAT;
    }

    public String getWALUTA_PLAT() {
        return WALUTA_PLAT;
    }

    public void setWALUTA_PLAT(String WALUTA_PLAT) {
        this.WALUTA_PLAT = WALUTA_PLAT;
    }

    public String getKURS_WALUTY_PLAT() {
        return KURS_WALUTY_PLAT;
    }

    public void setKURS_WALUTY_PLAT(String KURS_WALUTY_PLAT) {
        this.KURS_WALUTY_PLAT = KURS_WALUTY_PLAT;
    }

    public double getNOTOWANIE_WALUTY_ILE_PLAT() {
        return NOTOWANIE_WALUTY_ILE_PLAT;
    }

    public void setNOTOWANIE_WALUTY_ILE_PLAT(double NOTOWANIE_WALUTY_ILE_PLAT) {
        this.NOTOWANIE_WALUTY_ILE_PLAT = NOTOWANIE_WALUTY_ILE_PLAT;
    }

    public double getNOTOWANIE_WALUTY_ZA_ILE_PLAT() {
        return NOTOWANIE_WALUTY_ZA_ILE_PLAT;
    }

    public void setNOTOWANIE_WALUTY_ZA_ILE_PLAT(double NOTOWANIE_WALUTY_ZA_ILE_PLAT) {
        this.NOTOWANIE_WALUTY_ZA_ILE_PLAT = NOTOWANIE_WALUTY_ZA_ILE_PLAT;
    }

    public double getKWOTA_PLN_PLAT() {
        return KWOTA_PLN_PLAT;
    }

    public void setKWOTA_PLN_PLAT(double KWOTA_PLN_PLAT) {
        this.KWOTA_PLN_PLAT = KWOTA_PLN_PLAT;
    }

    public String getKIERUNEK() {
        return KIERUNEK;
    }

    public void setKIERUNEK(String KIERUNEK) {
        this.KIERUNEK = KIERUNEK;
    }

    public String getPODLEGA_ROZLICZENIU() {
        return PODLEGA_ROZLICZENIU;
    }

    public void setPODLEGA_ROZLICZENIU(String PODLEGA_ROZLICZENIU) {
        this.PODLEGA_ROZLICZENIU = PODLEGA_ROZLICZENIU;
    }

    public String getKONTO() {
        return KONTO;
    }

    public void setKONTO(String KONTO) {
        this.KONTO = KONTO;
    }

    public String getDATA_KURSU_PLAT() {
        return DATA_KURSU_PLAT;
    }

    public void setDATA_KURSU_PLAT(String DATA_KURSU_PLAT) {
        this.DATA_KURSU_PLAT = DATA_KURSU_PLAT;
    }

    public String getWALUTA_DOK() {
        return WALUTA_DOK;
    }

    public void setWALUTA_DOK(String WALUTA_DOK) {
        this.WALUTA_DOK = WALUTA_DOK;
    }
}
