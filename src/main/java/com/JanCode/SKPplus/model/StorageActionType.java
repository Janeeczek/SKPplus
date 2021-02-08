package com.JanCode.SKPplus.model;

public enum StorageActionType {
    CREATE("Stworzono","Dodano przedmiot do bazy danych."),
    EDIT("Edytowano","Zmodyfikowano przedmiot."),
    ARCHIVE("Zarchiwizowano","Zarchiwizowano przedmiot."),
    ADDQUANTITY("Dodano ilość","Dodano więcej przedmiotu."),
    GIVE("Wydano","Wydano przedmiot");


    public final String message;
    public final String plName;
    StorageActionType(String plName, String message) {
        this.plName = plName;
        this.message = message;
    }
}
