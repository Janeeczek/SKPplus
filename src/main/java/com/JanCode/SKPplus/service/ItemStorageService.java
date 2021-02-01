package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.exeception.QuantityTooSmallException;
import com.JanCode.SKPplus.model.Item;
import com.JanCode.SKPplus.model.ItemStorage;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.web.dto.ItemDto;
import com.JanCode.SKPplus.web.dto.WydajItemDto;

import java.util.List;

public interface ItemStorageService {
    List<Item> getAllItems();
    List<ItemStorage> getAllItemStorage();
    List<ItemStorage> getAllActualItemStorage();
    ItemStorage addItem(Item item, int quantity);
    void removeItemStorage(long itemStorageId);
    int getItemQuantity(long itemStorageId);
    ItemStorage setItemQuantity(long itemStorageIdm, int quantity);
    ItemStorage getItemStorage(long itemStorageId);
    ItemStorage updateItemStorage( ItemStorage itemStorage);
    ItemStorage wydajItem( WydajItemDto wydajItemDto, User user) throws QuantityTooSmallException;

}
