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
    @Autowired
    private ItemService itemService;


    @Override
    public List<Item> getAllItems() {
        List<ItemStorage> itemStorageList = itemStorageRepository.findAll();
        List<Item> itemList = new ArrayList<>();
        for (ItemStorage itemStorage : itemStorageList)
            itemList.add(itemStorage.getItem());
        return itemList;
    }

    @Override
    public List<ItemStorage> getAllItemStorage() {
        return itemStorageRepository.findAll();
    }

    @Override
    public ItemStorage addItem(Item item, int quantity) {
        ItemStorage itemStorage = new ItemStorage(item,quantity);
        return itemStorageRepository.save(itemStorage);
    }

    @Override
    public void removeItemStorage(long itemStorageId) {
        ItemStorage itemStorage = getItemStorage(itemStorageId);
        itemStorageRepository.delete(itemStorage);
    }

    @Override
    public int getItemQuantity(long itemStorageId) {
        ItemStorage itemStorage = getItemStorage(itemStorageId);

        return itemStorage.getQuantity();
    }

    @Override
    public ItemStorage setItemQuantity(long itemStorageId, int quantity) {
        ItemStorage itemStorage = getItemStorage(itemStorageId);
        itemStorage.setQuantity(quantity);
        itemService.deleteItem(itemStorage.getItem().getId());
        return itemStorageRepository.save(itemStorage);
    }

    @Override
    public ItemStorage getItemStorage(long itemStorageId) {
        return itemStorageRepository.getOne(itemStorageId);
    }
}
