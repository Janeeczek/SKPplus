package com.JanCode.SKPplus.web.dto;

import com.JanCode.SKPplus.constraint.ImageType;
import com.JanCode.SKPplus.constraint.ItemQuantity;
import com.JanCode.SKPplus.constraint.Tag;
import com.JanCode.SKPplus.model.ItemStorage;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

public class WydajItemDto {
    @NotBlank(message = "Numer badania nie może być pusty!")
    private String numerBadania;
    @Pattern(regexp="^(0|[1-9][0-9]*)$",message = "Proszę wybrać przedmiot")
    private String itemStorageId;
    @ItemQuantity(message = "Proszę określić ilość!")
    private int quantity;
    public WydajItemDto() {
    }

    public String getNumerBadania() {
        return numerBadania;
    }

    public void setNumerBadania(String numerBadania) {
        this.numerBadania = numerBadania;
    }

    public String getItemStorageId() {
        return itemStorageId;
    }

    public void setItemStorageId(String itemStorageId) {
        this.itemStorageId = itemStorageId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
