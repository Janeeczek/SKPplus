package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.Item;
import com.JanCode.SKPplus.model.ItemStorage;

import java.util.List;

public interface ItemStorageService {
    List<Item> getAllItems();
    ItemStorage addItem(Item item, int quantity);
    void removeItem(Item item);
    int getItemQuantity(Item item);
    ItemStorage setItemQuantity(Item item, int quantity);
    ItemStorage getItemStorage(Item item);
}
