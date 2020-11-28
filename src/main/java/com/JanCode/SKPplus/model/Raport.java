package com.JanCode.SKPplus.model;

import com.JanCode.SKPplus.model.raportModel.Kontrahent;
import com.JanCode.SKPplus.model.raportModel.RejestrSprzedazyVat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Raport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Kontrahent kontrahent;
    private RejestrSprzedazyVat rejestrSprzedazyVat;

}
