package com.JanCode.SKPplus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Adres {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String status;
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
}
