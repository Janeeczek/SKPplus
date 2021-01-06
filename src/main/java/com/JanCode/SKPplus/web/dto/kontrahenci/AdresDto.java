package com.JanCode.SKPplus.web.dto.kontrahenci;

import com.JanCode.SKPplus.model.raportModel.Adres;
import com.JanCode.SKPplus.util.AdapterCDATA;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "ADRES")
@XmlType(propOrder={"STATUS", "NAZWA1", "NAZWA2", "NAZWA3","ULICA","NR_DOMU","MIASTO","KOD_POCZTOWY","POCZTA","NIP_KRAJ","NIP"})
public class AdresDto {
    private String STATUS;

    private String NAZWA1;
    private String NAZWA2;
    private String NAZWA3;
    private String ULICA;
    private String NR_DOMU;
    private String MIASTO;
    private String KOD_POCZTOWY;
    private String POCZTA;
    private String NIP_KRAJ;
    private String NIP;

    public AdresDto() {
    }

    public AdresDto(String STATUS, String NAZWA1, String NAZWA2, String NAZWA3, String ULICA, String NR_DOMU, String MIASTO, String KOD_POCZTOWY, String POCZTA, String NIP_KRAJ, String NIP) {
        this.STATUS = STATUS;
        this.NAZWA1 = NAZWA1;
        this.NAZWA2 = NAZWA2;
        this.NAZWA3 = NAZWA3;
        this.ULICA = ULICA;
        this.NR_DOMU = NR_DOMU;
        this.MIASTO = MIASTO;
        this.KOD_POCZTOWY = KOD_POCZTOWY;
        this.POCZTA = POCZTA;
        this.NIP_KRAJ = NIP_KRAJ;
        this.NIP = NIP;
    }
    public AdresDto(Adres adres) {
        this.STATUS = adres.getStatus();
        this.NAZWA1 = adres.getNazwa1();
        this.NAZWA2 = adres.getNazwa2();
        this.NAZWA3 = adres.getNazwa3();
        this.ULICA = adres.getUlica();
        this.NR_DOMU = adres.getNr_domu();
        this.MIASTO = adres.getMiasto();
        this.KOD_POCZTOWY = adres.getKod_pocztowy();
        this.POCZTA = adres.getPoczta();
        this.NIP_KRAJ = adres.getNip_kraj();
        this.NIP = adres.getNip();
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    public String getNAZWA1() {
        return NAZWA1;
    }

    public void setNAZWA1(String NAZWA1) {
        this.NAZWA1 = NAZWA1;
    }
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    public String getNAZWA2() {
        return NAZWA2;
    }

    public void setNAZWA2(String NAZWA2) {
        this.NAZWA2 = NAZWA2;
    }
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    public String getNAZWA3() {
        return NAZWA3;
    }

    public void setNAZWA3(String NAZWA3) {
        this.NAZWA3 = NAZWA3;
    }
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    public String getULICA() {
        return ULICA;
    }

    public void setULICA(String ULICA) {
        this.ULICA = ULICA;
    }
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    public String getNR_DOMU() {
        return NR_DOMU;
    }

    public void setNR_DOMU(String NR_DOMU) {
        this.NR_DOMU = NR_DOMU;
    }
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    public String getMIASTO() {
        return MIASTO;
    }

    public void setMIASTO(String MIASTO) {
        this.MIASTO = MIASTO;
    }
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    public String getKOD_POCZTOWY() {
        return KOD_POCZTOWY;
    }

    public void setKOD_POCZTOWY(String KOD_POCZTOWY) {
        this.KOD_POCZTOWY = KOD_POCZTOWY;
    }
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    public String getPOCZTA() {
        return POCZTA;
    }

    public void setPOCZTA(String POCZTA) {
        this.POCZTA = POCZTA;
    }
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    public String getNIP_KRAJ() {
        return NIP_KRAJ;
    }

    public void setNIP_KRAJ(String NIP_KRAJ) {
        this.NIP_KRAJ = NIP_KRAJ;
    }
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }
}
