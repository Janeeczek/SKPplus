package com.JanCode.SKPplus.web.dto;

import com.JanCode.SKPplus.constraint.ImageType;
import com.JanCode.SKPplus.constraint.Tag;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

public class ItemDto {
    @NotBlank(message = "Nazwa nie może być pusta!")
    private String name;
    @NotBlank(message = "Opis nie może być pusty!")
    private String description;
    @NotBlank(message = "Tag nie może być pusty!")
    @Tag(message = "Tag nie może mieć wiecej niż 5 znaków!")
    private String tag;
    private int quantity;
    @ImageType(message = "Błedny typ zdjęcia!")
    private MultipartFile image;

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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}