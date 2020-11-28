package com.JanCode.SKPplus.model.raportModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Pozycja {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
}
