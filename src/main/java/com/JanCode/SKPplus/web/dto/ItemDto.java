package com.JanCode.SKPplus.web.dto;

import com.JanCode.SKPplus.constraint.ImageType;
import com.JanCode.SKPplus.constraint.Tag;
import com.JanCode.SKPplus.model.ItemStorage;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.Base64;

public class ItemDto {
    @NotBlank(message = "Nazwa nie może być pusta!")
    private String name;
    @NotBlank(message = "Opis nie może być pusty!")
    private String description;
    private int quantity;
    @ImageType(message = "Błedny typ zdjęcia!")
    private MultipartFile image;

    public ItemDto() {
    }
    public ItemDto(ItemStorage itemStorage) {
        this.name = itemStorage.getItem().getName();
        this.description = itemStorage.getItem().getDescription();
        this.image = new MockMultipartFile("image",null,itemStorage.getItem().getContentType(),itemStorage.getItem().getImage());
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
