package com.JanCode.SKPplus.model.raportModel;

import com.JanCode.SKPplus.web.dto.rejestrySprzedazy.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class RejestrSprzedazyVat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String modul;
    private String typ;
    private String rejestr;
    private LocalDate  data_wystawienia;
    private LocalDate data_sprzedazy;
    private LocalDate  termin;
    private String numer;
    private String wewnetrzna;
    private String fiskalna;
    private String detaliczna;
    private String typ_podmiotu;
    private String podmiot;
    private String nazwa1;
    private String nazwa2;
    private String nazwa3;
    private String ulica;
    private String nr_domu;
    private String miasto;
    private String kod_pocztowy;
    private String poczta;
    private String nip_kraj;
    private String nip;
    private String kategoria;
    private String waluta;
    private String forma_platnosci;
    private String kurs_waluty;
    private String notowanie_waluty_ile;
    private String notowanie_waluty_za_ile;
    private LocalDate data_kursu;
    private String kurs_do_ksiegowania;
    private String kurs_waluty_2;
    private String notowanie_waluty_ile_2;
    private LocalDate  data_kursu_2;


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "rejestr_sprzedazy_vat_id",referencedColumnName = "id")
    private List<Pozycja> pozycje ;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "kwotyDodatkowe_id")
    private KwotyDodatkowe kwotyDodatkowe;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "rejestr_sprzedazy_vat_id",referencedColumnName = "id")
    private List<Platnosc> platnosci;

    public RejestrSprzedazyVat() {
    }

    public RejestrSprzedazyVat(long id, String modul, String typ, String rejestr, LocalDate  data_wystawienia, LocalDate  data_sprzedazy, LocalDate  termin, String numer, String wewnetrzna, String fiskalna, String detaliczna, String typ_podmiotu, String podmiot, String nazwa1, String nazwa2, String nazwa3, String ulica, String nr_domu, String miasto, String kod_pocztowy, String poczta, String nip_kraj, String nip, String kategoria, String waluta, String forma_platnosci, String kurs_waluty, String notowanie_waluty_ile, String notowanie_waluty_za_ile, LocalDate  data_kursu, String kurs_do_ksiegowania, String kurs_waluty_2, String notowanie_waluty_ile_2, LocalDate  data_kursu_2, List<Pozycja> pozycje, KwotyDodatkowe kwotyDodatkowe, List<Platnosc> platnosci) {
        this.id = id;
        this.modul = modul;
        this.typ = typ;
        this.rejestr = rejestr;
        this.data_wystawienia = data_wystawienia;
        this.data_sprzedazy = data_sprzedazy;
        this.termin = termin;
        this.numer = numer;
        this.wewnetrzna = wewnetrzna;
        this.fiskalna = fiskalna;
        this.detaliczna = detaliczna;
        this.typ_podmiotu = typ_podmiotu;
        this.podmiot = podmiot;
        this.nazwa1 = nazwa1;
        this.nazwa2 = nazwa2;
        this.nazwa3 = nazwa3;
        this.ulica = ulica;
        this.nr_domu = nr_domu;
        this.miasto = miasto;
        this.kod_pocztowy = kod_pocztowy;
        this.poczta = poczta;
        this.nip_kraj = nip_kraj;
        this.nip = nip;
        this.kategoria = kategoria;
        this.waluta = waluta;
        this.forma_platnosci = forma_platnosci;
        this.kurs_waluty = kurs_waluty;
        this.notowanie_waluty_ile = notowanie_waluty_ile;
        this.notowanie_waluty_za_ile = notowanie_waluty_za_ile;
        this.data_kursu = data_kursu;
        this.kurs_do_ksiegowania = kurs_do_ksiegowania;
        this.kurs_waluty_2 = kurs_waluty_2;
        this.notowanie_waluty_ile_2 = notowanie_waluty_ile_2;
        this.data_kursu_2 = data_kursu_2;
        this.pozycje = pozycje;
        this.kwotyDodatkowe = kwotyDodatkowe;
        this.platnosci = platnosci;
    }
    public RejestrSprzedazyVat(RejestrSprzedazyVatDto rejestrSprzedazyVatDto) {
        this.modul = rejestrSprzedazyVatDto.getMODUL();
        this.typ = rejestrSprzedazyVatDto.getTYP();
        this.rejestr = rejestrSprzedazyVatDto.getREJESTR();
        this.data_wystawienia = LocalDate.parse(rejestrSprzedazyVatDto.getDATA_WYSTAWIENIA());
        this.data_sprzedazy = LocalDate.parse(rejestrSprzedazyVatDto.getDATA_SPRZEDAZY());
        this.termin = LocalDate.parse(rejestrSprzedazyVatDto.getTERMIN());
        this.numer = rejestrSprzedazyVatDto.getNUMER();
        this.wewnetrzna = rejestrSprzedazyVatDto.getWEWNETRZNA();
        this.fiskalna = rejestrSprzedazyVatDto.getFISKALNA();
        this.detaliczna = rejestrSprzedazyVatDto.getDETALICZNA();
        this.typ_podmiotu = rejestrSprzedazyVatDto.getTYP_PODMIOTU();
        this.podmiot = rejestrSprzedazyVatDto.getPODMIOT();
        this.nazwa1 = rejestrSprzedazyVatDto.getNAZWA1();
        this.nazwa2 = rejestrSprzedazyVatDto.getNAZWA2();
        this.nazwa3 = rejestrSprzedazyVatDto.getNAZWA3();
        this.ulica = rejestrSprzedazyVatDto.getULICA();
        this.nr_domu = rejestrSprzedazyVatDto.getNR_DOMU();
        this.miasto = rejestrSprzedazyVatDto.getMIASTO();
        this.kod_pocztowy = rejestrSprzedazyVatDto.getKOD_POCZTOWY();
        this.poczta = rejestrSprzedazyVatDto.getPOCZTA();
        this.nip_kraj = rejestrSprzedazyVatDto.getNIP_KRAJ();
        this.nip = rejestrSprzedazyVatDto.getNIP();
        this.kategoria = rejestrSprzedazyVatDto.getKATEGORIA();
        this.waluta = rejestrSprzedazyVatDto.getWALUTA();
        this.forma_platnosci = rejestrSprzedazyVatDto.getFORMA_PLATNOSCI();
        this.kurs_waluty = rejestrSprzedazyVatDto.getKURS_WALUTY();
        this.notowanie_waluty_ile = rejestrSprzedazyVatDto.getNOTOWANIE_WALUTY_ILE();
        this.notowanie_waluty_za_ile = rejestrSprzedazyVatDto.getNOTOWANIE_WALUTY_ZA_ILE();
        this.data_kursu = LocalDate.parse(rejestrSprzedazyVatDto.getDATA_KURSU());
        this.kurs_do_ksiegowania = rejestrSprzedazyVatDto.getKURS_DO_KSIEGOWANIA();
        this.kurs_waluty_2 = rejestrSprzedazyVatDto.getKURS_WALUTY_2();
        this.notowanie_waluty_ile_2 = rejestrSprzedazyVatDto.getNOTOWANIE_WALUTY_ILE_2();
        this.data_kursu_2 = LocalDate.parse(rejestrSprzedazyVatDto.getDATA_KURSU_2());
        this.kwotyDodatkowe = new KwotyDodatkowe(rejestrSprzedazyVatDto.getKWOTY_DODATKOWE());

        List<PozycjaDto> pozDtoList =new ArrayList<>(rejestrSprzedazyVatDto.getPOZYCJE().getPOZYCJA());
        List<Pozycja> pozList = new ArrayList<>();
        List<PlatnoscDto> platDtoList =new ArrayList<>(rejestrSprzedazyVatDto.getPLATNOSCI().getPLATNOSC());
        List<Platnosc> platList = new ArrayList<>();
        for (int i = 0; i < pozDtoList.size(); i++) {
            pozList.add(new Pozycja(pozDtoList.get(i)));
        }
        for (int i = 0; i < platDtoList.size(); i++) {
            platList.add(new Platnosc(platDtoList.get(i)));
        }
        this.pozycje = pozList;

        this.platnosci = platList;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModul() {
        return modul;
    }

    public void setModul(String modul) {
        this.modul = modul;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getRejestr() {
        return rejestr;
    }

    public void setRejestr(String rejestr) {
        this.rejestr = rejestr;
    }

    public LocalDate  getData_wystawienia() {
        return data_wystawienia;
    }

    public void setData_wystawienia(LocalDate  data_wystawienia) {
        this.data_wystawienia = data_wystawienia;
    }

    public LocalDate  getData_sprzedazy() {
        return data_sprzedazy;
    }

    public void setData_sprzedazy(LocalDate  data_sprzedazy) {
        this.data_sprzedazy = data_sprzedazy;
    }

    public LocalDate  getTermin() {
        return termin;
    }

    public void setTermin(LocalDate  termin) {
        this.termin = termin;
    }

    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public String getWewnetrzna() {
        return wewnetrzna;
    }

    public void setWewnetrzna(String wewnetrzna) {
        this.wewnetrzna = wewnetrzna;
    }

    public String getFiskalna() {
        return fiskalna;
    }

    public void setFiskalna(String fiskalna) {
        this.fiskalna = fiskalna;
    }

    public String getDetaliczna() {
        return detaliczna;
    }

    public void setDetaliczna(String detaliczna) {
        this.detaliczna = detaliczna;
    }

    public String getTyp_podmiotu() {
        return typ_podmiotu;
    }

    public void setTyp_podmiotu(String typ_podmiotu) {
        this.typ_podmiotu = typ_podmiotu;
    }

    public String getPodmiot() {
        return podmiot;
    }

    public void setPodmiot(String podmiot) {
        this.podmiot = podmiot;
    }

    public String getNazwa1() {
        return nazwa1;
    }

    public void setNazwa1(String nazwa1) {
        this.nazwa1 = nazwa1;
    }

    public String getNazwa2() {
        return nazwa2;
    }

    public void setNazwa2(String nazwa2) {
        this.nazwa2 = nazwa2;
    }

    public String getNazwa3() {
        return nazwa3;
    }

    public void setNazwa3(String nazwa3) {
        this.nazwa3 = nazwa3;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNr_domu() {
        return nr_domu;
    }

    public void setNr_domu(String nr_domu) {
        this.nr_domu = nr_domu;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getKod_pocztowy() {
        return kod_pocztowy;
    }

    public void setKod_pocztowy(String kod_pocztowy) {
        this.kod_pocztowy = kod_pocztowy;
    }

    public String getPoczta() {
        return poczta;
    }

    public void setPoczta(String poczta) {
        this.poczta = poczta;
    }

    public String getNip_kraj() {
        return nip_kraj;
    }

    public void setNip_kraj(String nip_kraj) {
        this.nip_kraj = nip_kraj;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public String getWaluta() {
        return waluta;
    }

    public void setWaluta(String waluta) {
        this.waluta = waluta;
    }

    public String getForma_platnosci() {
        return forma_platnosci;
    }

    public void setForma_platnosci(String forma_platnosci) {
        this.forma_platnosci = forma_platnosci;
    }

    public String getKurs_waluty() {
        return kurs_waluty;
    }

    public void setKurs_waluty(String kurs_waluty) {
        this.kurs_waluty = kurs_waluty;
    }

    public String getNotowanie_waluty_ile() {
        return notowanie_waluty_ile;
    }

    public void setNotowanie_waluty_ile(String notowanie_waluty_ile) {
        this.notowanie_waluty_ile = notowanie_waluty_ile;
    }

    public String getNotowanie_waluty_za_ile() {
        return notowanie_waluty_za_ile;
    }

    public void setNotowanie_waluty_za_ile(String notowanie_waluty_za_ile) {
        this.notowanie_waluty_za_ile = notowanie_waluty_za_ile;
    }

    public LocalDate getData_kursu() {
        return data_kursu;
    }

    public void setData_kursu(LocalDate data_kursu) {
        this.data_kursu = data_kursu;
    }

    public String getKurs_do_ksiegowania() {
        return kurs_do_ksiegowania;
    }

    public void setKurs_do_ksiegowania(String kurs_do_ksiegowania) {
        this.kurs_do_ksiegowania = kurs_do_ksiegowania;
    }

    public String getKurs_waluty_2() {
        return kurs_waluty_2;
    }

    public void setKurs_waluty_2(String kurs_waluty_2) {
        this.kurs_waluty_2 = kurs_waluty_2;
    }

    public String getNotowanie_waluty_ile_2() {
        return notowanie_waluty_ile_2;
    }

    public void setNotowanie_waluty_ile_2(String notowanie_waluty_ile_2) {
        this.notowanie_waluty_ile_2 = notowanie_waluty_ile_2;
    }

    public LocalDate getData_kursu_2() {
        return data_kursu_2;
    }

    public void setData_kursu_2(LocalDate data_kursu_2) {
        this.data_kursu_2 = data_kursu_2;
    }

    public List<Pozycja> getPozycje() {
        return pozycje;
    }

    public void setPozycje(List<Pozycja> pozycje) {
        this.pozycje = pozycje;
    }

    public KwotyDodatkowe getKwotyDodatkowe() {
        return kwotyDodatkowe;
    }

    public void setKwotyDodatkowe(KwotyDodatkowe kwotyDodatkowe) {
        this.kwotyDodatkowe = kwotyDodatkowe;
    }

    public List<Platnosc> getPlatnosci() {
        return platnosci;
    }

    public void setPlatnosci(List<Platnosc> platnosci) {
        this.platnosci = platnosci;
    }
}

