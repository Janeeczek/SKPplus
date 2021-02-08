package com.JanCode.SKPplus.web.dto;

import org.springframework.web.multipart.MultipartFile;

public class RaportDto {
    private MultipartFile raport;
    private String name;
    public RaportDto() {
    }
    public RaportDto(MultipartFile raport) {
        this.raport = raport;
    }

    public MultipartFile getRaport() {
        return raport;
    }

    public void setRaport(MultipartFile raport) {
        this.raport = raport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
