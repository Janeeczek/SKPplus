package com.JanCode.SKPplus.web.dto;

import org.springframework.web.multipart.MultipartFile;

public class RaportDto {
    private MultipartFile raport;
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
}
