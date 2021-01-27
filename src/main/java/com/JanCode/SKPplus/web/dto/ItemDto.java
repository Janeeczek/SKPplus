package com.JanCode.SKPplus.web.dto;

import javax.validation.constraints.NotBlank;

public class ItemDto {
    @NotBlank(message = "Nazwa nie może być pusta!")
    private String name;
    @NotBlank(message = "Opis nie może być pusta!")
    private String description;
    @NotBlank(message = "Tag nie może być pusty!")
    private String tag;
    private byte[] image;

    public ItemDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
