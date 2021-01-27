package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.Item;
import com.JanCode.SKPplus.model.ItemStorage;
import com.JanCode.SKPplus.repository.ItemStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ItemStorageServiceImpl implements ItemStorageService {
    @Autowired
    private ItemStorageRepository itemStorageRepository;


    @Override
    public List<Item> getAllItems() {
        List<ItemStorage> itemStorageList = itemStorageRepository.findAll();
        List<Item> itemList = new ArrayList<>();
        for (ItemStorage itemStorage : itemStorageList)
            itemList.add(itemStorage.getItem());
        return itemList;
    }

    @Override
    public ItemStorage addItem(Item item, int quantity) {
        return itemStorageRepository.save(new ItemStorage(item, quantity));
    }

    @Override
    public void removeItem(Item item) {
        ItemStorage itemStorage = getItemStorage(item);
        itemStorageRepository.delete(itemStorage);
    }

    @Override
    public int getItemQuantity(Item item) {
        return itemStorageRepository.getItemQuantity(item.getId());
    }

    @Override
    public ItemStorage setItemQuantity(Item item, int quantity) {
        ItemStorage itemStorage = getItemStorage(item);
        itemStorage.setQuantity(quantity);
        return itemStorageRepository.save(itemStorage);
    }

    @Override
    public ItemStorage getItemStorage(Item item) {
        return itemStorageRepository.getItemStorageByItemId(item.getId());
    }
}
