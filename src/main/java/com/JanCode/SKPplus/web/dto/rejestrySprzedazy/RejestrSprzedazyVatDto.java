package com.JanCode.SKPplus.web.dto.rejestrySprzedazy;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Set;
@XmlRootElement(name = "REJESTR_SPRZEDAZY_VAT")
@XmlType(propOrder={ "MODUL","TYP","REJESTR","DATA_WYSTAWIENIA","DATA_SPRZEDAZY","TERMIN","NUMER","WEWNETRZNA","FISKALNA",
        "DETALICZNA","TYP_PODMIOTU","PODMIOT","NAZWA1","NAZWA2","NAZWA3","NIP_KRAJ","NIP","ULICA","NR_DOMU","MIASTO","KOD_POCZTOWY",
        "POCZTA","KATEGORIA","WALUTA","FORMA_PLATNOSCI","KURS_WALUTY","NOTOWANIE_WALUTY_ILE","NOTOWANIE_WALUTY_ZA_ILE","DATA_KURSU",
        "KURS_DO_KSIEGOWANIA","KURS_WALUTY_2","NOTOWANIE_WALUTY_ILE_2","DATA_KURSU_2","POZYCJE","KWOTY_DODATKOWE","PLATNOSCI"})
public class RejestrSprzedazyVatDto {
    private String MODUL;
    private String TYP;
    private String REJESTR;
    private String DATA_WYSTAWIENIA;
    private String DATA_SPRZEDAZY;
    private String TERMIN;
    private String NUMER;
    private String WEWNETRZNA;
    private String FISKALNA; //9
    private String DETALICZNA;
    private String TYP_PODMIOTU;
    private String PODMIOT;
    private String NAZWA1;
    private String NAZWA2;
    private String NAZWA3;
    private String NIP_KRAJ;
    private String NIP;
    private String ULICA;
    private String NR_DOMU;
    private String MIASTO;
    private String KOD_POCZTOWY;//12
    private String POCZTA;
    private String KATEGORIA;
    private String WALUTA;
    private String FORMA_PLATNOSCI;
    private String KURS_WALUTY;
    private String NOTOWANIE_WALUTY_ILE;
    private String NOTOWANIE_WALUTY_ZA_ILE;
    private String DATA_KURSU;//8
    private String KURS_DO_KSIEGOWANIA;
    private String KURS_WALUTY_2;
    private String NOTOWANIE_WALUTY_ILE_2;
    private String DATA_KURSU_2;
    private PozycjeDto POZYCJE;
    private KwotyDodatkoweDto KWOTY_DODATKOWE;
    private PlatnosciDto PLATNOSCI;

    public RejestrSprzedazyVatDto( String MODUL, String TYP, String REJESTR, String DATA_WYSTAWIENIA, String DATA_SPRZEDAZY, String TERMIN, String NUMER, String WEWNETRZNA, String FISKALNA, String DETALICZNA, String TYP_PODMIOTU, String PODMIOT, String NAZWA1, String NAZWA2, String NAZWA3, String NIP_KRAJ, String NIP, String ULICA, String NR_DOMU, String MIASTO, String KOD_POCZTOWY, String POCZTA, String KATEGORIA, String WALUTA, String FORMA_PLATNOSCI, String KURS_WALUTY, String NOTOWANIE_WALUTY_ILE, String NOTOWANIE_WALUTY_ZA_ILE, String DATA_KURSU, String KURS_DO_KSIEGOWANIA, String KURS_WALUTY_2, String NOTOWANIE_WALUTY_ILE_2, String DATA_KURSU_2, PozycjeDto POZYCJE, KwotyDodatkoweDto KWOTY_DODATKOWE, PlatnosciDto PLATNOSCI) {
        this.MODUL = MODUL;
        this.TYP = TYP;
        this.REJESTR = REJESTR;
        this.DATA_WYSTAWIENIA = DATA_WYSTAWIENIA;
        this.DATA_SPRZEDAZY = DATA_SPRZEDAZY;
        this.TERMIN = TERMIN;
        this.NUMER = NUMER;
        this.WEWNETRZNA = WEWNETRZNA;
        this.FISKALNA = FISKALNA;
        this.DETALICZNA = DETALICZNA;
        this.TYP_PODMIOTU = TYP_PODMIOTU;
        this.PODMIOT = PODMIOT;
        this.NAZWA1 = NAZWA1;
        this.NAZWA2 = NAZWA2;
        this.NAZWA3 = NAZWA3;
        this.NIP_KRAJ = NIP_KRAJ;
        this.NIP = NIP;
        this.ULICA = ULICA;
        this.NR_DOMU = NR_DOMU;
        this.MIASTO = MIASTO;
        this.KOD_POCZTOWY = KOD_POCZTOWY;
        this.POCZTA = POCZTA;
        this.KATEGORIA = KATEGORIA;
        this.WALUTA = WALUTA;
        this.FORMA_PLATNOSCI = FORMA_PLATNOSCI;
        this.KURS_WALUTY = KURS_WALUTY;
        this.NOTOWANIE_WALUTY_ILE = NOTOWANIE_WALUTY_ILE;
        this.NOTOWANIE_WALUTY_ZA_ILE = NOTOWANIE_WALUTY_ZA_ILE;
        this.DATA_KURSU = DATA_KURSU;
        this.KURS_DO_KSIEGOWANIA = KURS_DO_KSIEGOWANIA;
        this.KURS_WALUTY_2 = KURS_WALUTY_2;
        this.NOTOWANIE_WALUTY_ILE_2 = NOTOWANIE_WALUTY_ILE_2;
        this.DATA_KURSU_2 = DATA_KURSU_2;
        this.POZYCJE = POZYCJE;
        this.KWOTY_DODATKOWE = KWOTY_DODATKOWE;
        this.PLATNOSCI = PLATNOSCI;
    }

    public RejestrSprzedazyVatDto() {
    }

    public String getMODUL() {
        return MODUL;
    }

    public void setMODUL(String MODUL) {
        this.MODUL = MODUL;
    }

    public String getTYP() {
        return TYP;
    }

    public void setTYP(String TYP) {
        this.TYP = TYP;
    }

    public String getREJESTR() {
        return REJESTR;
    }

    public void setREJESTR(String REJESTR) {
        this.REJESTR = REJESTR;
    }

    public String getDATA_WYSTAWIENIA() {
        return DATA_WYSTAWIENIA;
    }

    public void setDATA_WYSTAWIENIA(String DATA_WYSTAWIENIA) {
        this.DATA_WYSTAWIENIA = DATA_WYSTAWIENIA;
    }

    public String getDATA_SPRZEDAZY() {
        return DATA_SPRZEDAZY;
    }

    public void setDATA_SPRZEDAZY(String DATA_SPRZEDAZY) {
        this.DATA_SPRZEDAZY = DATA_SPRZEDAZY;
    }

    public String getTERMIN() {
        return TERMIN;
    }

    public void setTERMIN(String TERMIN) {
        this.TERMIN = TERMIN;
    }

    public String getNUMER() {
        return NUMER;
    }

    public void setNUMER(String NUMER) {
        this.NUMER = NUMER;
    }

    public String getWEWNETRZNA() {
        return WEWNETRZNA;
    }

    public void setWEWNETRZNA(String WEWNETRZNA) {
        this.WEWNETRZNA = WEWNETRZNA;
    }

    public String getFISKALNA() {
        return FISKALNA;
    }

    public void setFISKALNA(String FISKALNA) {
        this.FISKALNA = FISKALNA;
    }

    public String getDETALICZNA() {
        return DETALICZNA;
    }

    public void setDETALICZNA(String DETALICZNA) {
        this.DETALICZNA = DETALICZNA;
    }

    public String getTYP_PODMIOTU() {
        return TYP_PODMIOTU;
    }

    public void setTYP_PODMIOTU(String TYP_PODMIOTU) {
        this.TYP_PODMIOTU = TYP_PODMIOTU;
    }

    public String getPODMIOT() {
        return PODMIOT;
    }

    public void setPODMIOT(String PODMIOT) {
        this.PODMIOT = PODMIOT;
    }

    public String getNAZWA1() {
        return NAZWA1;
    }

    public void setNAZWA1(String NAZWA1) {
        this.NAZWA1 = NAZWA1;
    }

    public String getNAZWA2() {
        return NAZWA2;
    }

    public void setNAZWA2(String NAZWA2) {
        this.NAZWA2 = NAZWA2;
    }

    public String getNAZWA3() {
        return NAZWA3;
    }

    public void setNAZWA3(String NAZWA3) {
        this.NAZWA3 = NAZWA3;
    }

    public String getNIP_KRAJ() {
        return NIP_KRAJ;
    }

    public void setNIP_KRAJ(String NIP_KRAJ) {
        this.NIP_KRAJ = NIP_KRAJ;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public String getULICA() {
        return ULICA;
    }

    public void setULICA(String ULICA) {
        this.ULICA = ULICA;
    }

    public String getNR_DOMU() {
        return NR_DOMU;
    }

    public void setNR_DOMU(String NR_DOMU) {
        this.NR_DOMU = NR_DOMU;
    }

    public String getMIASTO() {
        return MIASTO;
    }

    public void setMIASTO(String MIASTO) {
        this.MIASTO = MIASTO;
    }

    public String getKOD_POCZTOWY() {
        return KOD_POCZTOWY;
    }

    public void setKOD_POCZTOWY(String KOD_POCZTOWY) {
        this.KOD_POCZTOWY = KOD_POCZTOWY;
    }

    public String getPOCZTA() {
        return POCZTA;
    }

    public void setPOCZTA(String POCZTA) {
        this.POCZTA = POCZTA;
    }

    public String getKATEGORIA() {
        return KATEGORIA;
    }

    public void setKATEGORIA(String KATEGORIA) {
        this.KATEGORIA = KATEGORIA;
    }

    public String getWALUTA() {
        return WALUTA;
    }

    public void setWALUTA(String WALUTA) {
        this.WALUTA = WALUTA;
    }

    public String getFORMA_PLATNOSCI() {
        return FORMA_PLATNOSCI;
    }

    public void setFORMA_PLATNOSCI(String FORMA_PLATNOSCI) {
        this.FORMA_PLATNOSCI = FORMA_PLATNOSCI;
    }

    public String getKURS_WALUTY() {
        return KURS_WALUTY;
    }

    public void setKURS_WALUTY(String KURS_WALUTY) {
        this.KURS_WALUTY = KURS_WALUTY;
    }

    public String getNOTOWANIE_WALUTY_ILE() {
        return NOTOWANIE_WALUTY_ILE;
    }

    public void setNOTOWANIE_WALUTY_ILE(String NOTOWANIE_WALUTY_ILE) {
        this.NOTOWANIE_WALUTY_ILE = NOTOWANIE_WALUTY_ILE;
    }

    public String getNOTOWANIE_WALUTY_ZA_ILE() {
        return NOTOWANIE_WALUTY_ZA_ILE;
    }

    public void setNOTOWANIE_WALUTY_ZA_ILE(String NOTOWANIE_WALUTY_ZA_ILE) {
        this.NOTOWANIE_WALUTY_ZA_ILE = NOTOWANIE_WALUTY_ZA_ILE;
    }

    public String getDATA_KURSU() {
        return DATA_KURSU;
    }

    public void setDATA_KURSU(String DATA_KURSU) {
        this.DATA_KURSU = DATA_KURSU;
    }

    public String getKURS_DO_KSIEGOWANIA() {
        return KURS_DO_KSIEGOWANIA;
    }

    public void setKURS_DO_KSIEGOWANIA(String KURS_DO_KSIEGOWANIA) {
        this.KURS_DO_KSIEGOWANIA = KURS_DO_KSIEGOWANIA;
    }

    public String getKURS_WALUTY_2() {
        return KURS_WALUTY_2;
    }

    public void setKURS_WALUTY_2(String KURS_WALUTY_2) {
        this.KURS_WALUTY_2 = KURS_WALUTY_2;
    }

    public String getNOTOWANIE_WALUTY_ILE_2() {
        return NOTOWANIE_WALUTY_ILE_2;
    }

    public void setNOTOWANIE_WALUTY_ILE_2(String NOTOWANIE_WALUTY_ILE_2) {
        this.NOTOWANIE_WALUTY_ILE_2 = NOTOWANIE_WALUTY_ILE_2;
    }

    public String getDATA_KURSU_2() {
        return DATA_KURSU_2;
    }

    public void setDATA_KURSU_2(String DATA_KURSU_2) {
        this.DATA_KURSU_2 = DATA_KURSU_2;
    }
    public PozycjeDto getPOZYCJE() {
        return POZYCJE;
    }

    public void setPOZYCJE(PozycjeDto POZYCJE) {
        this.POZYCJE = POZYCJE;
    }

    public KwotyDodatkoweDto getKWOTY_DODATKOWE() {
        return KWOTY_DODATKOWE;
    }

    public void setKWOTY_DODATKOWE(KwotyDodatkoweDto KWOTY_DODATKOWE) {
        this.KWOTY_DODATKOWE = KWOTY_DODATKOWE;
    }
    public PlatnosciDto getPLATNOSCI() {
        return PLATNOSCI;
    }

    public void setPLATNOSCI(PlatnosciDto PLATNOSCI) {
        this.PLATNOSCI = PLATNOSCI;
    }

}
