package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.Item;
import com.JanCode.SKPplus.model.ItemStorage;
import com.JanCode.SKPplus.repository.ItemStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
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
        System.err.println("ZAPISUJE STORAGE");
        ItemStorage itemStorage = new ItemStorage(item,quantity);
        return itemStorageRepository.save(itemStorage);
    }

    @Override
    public void removeItem(Item item) {
        ItemStorage itemStorage = getItemStorage(item);
        itemStorageRepository.delete(itemStorage);
    }

    @Override
    public int getItemQuantity(Item item) {
        ItemStorage itemStorage = getItemStorage(item);

        return itemStorage.getQuantity();
    }

    @Override
    public ItemStorage setItemQuantity(Item item, int quantity) {
        ItemStorage itemStorage = getItemStorage(item);
        itemStorage.setQuantity(quantity);
        return itemStorageRepository.save(itemStorage);
    }

    @Override
    public ItemStorage getItemStorage(Item item) {
        return itemStorageRepository.findItemStorageByItemId(item.getId());
    }
}
