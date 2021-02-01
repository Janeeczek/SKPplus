package com.JanCode.SKPplus.web.dto;

import com.JanCode.SKPplus.constraint.ImageType;
import com.JanCode.SKPplus.constraint.ItemQuantity;
import com.JanCode.SKPplus.constraint.Tag;
import com.JanCode.SKPplus.model.ItemStorage;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class WydajItemDto {
    @NotBlank(message = "Nazwa nie może być pusta!")
    private String numerBadania;
    @NotNull(message = "Proszę wybrać przedmiot")
    private long itemStorageId;
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

    public long getItemStorageId() {
        return itemStorageId;
    }

    public void setItemStorageId(long itemStorageId) {
        this.itemStorageId = itemStorageId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
