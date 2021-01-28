package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.Item;
import com.JanCode.SKPplus.model.ItemStorage;

import java.util.List;

public interface ItemStorageService {
    List<Item> getAllItems();
    List<ItemStorage> getAllItemStorage();
    ItemStorage addItem(Item item, int quantity);
    void removeItemStorage(long itemStorageId);
    int getItemQuantity(long itemStorageId);
    ItemStorage setItemQuantity(long itemStorageIdm, int quantity);
    ItemStorage getItemStorage(long itemStorageId);

}
